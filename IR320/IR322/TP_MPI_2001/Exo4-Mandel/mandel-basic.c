#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "pvm3.h"

#if defined(__GNUC__) && (__GNUC__ >= 3)
#define ATTRIBUTE(x) __attribute__(x)
#else
#define ATTRIBUTE(x) /**/
#endif

#define MIN(_x, _y) ((_x) > (_y) ? (_y) : (_x))
#define ABS(_x) ((_x) >= 0 ? (_x) : -(_x))

#define MAXCHILDREN 16
#define NUMCHILDREN 1

/* N'hesitez pas a changer MAXX .*/
#define MAXX 500
#define MAXY (MAXX * 3 / 4)

#define NX (2 * MAXX + 1)
#define NY (2 * MAXY + 1)

#define NBITER 550

static int mandel(double, double);
static void pvm_ferror(const char*, int) ATTRIBUTE((noreturn));
char *get_host_by_tid(struct pvmhostinfo *hostp, int nhost, int tid) ;
int dump_ppm(const char *, int[NX][NY]);
int cases[NX][NY];

int
main(int argc, char **argv)
{
    int tid;
    int parent;

    struct pvmhostinfo *hostp;
    int nhost, narch;

    tid = pvm_mytid();
    if(tid < 0) pvm_ferror("pvm_mytid", 1);

    parent = pvm_parent();

    if(parent == PvmNoParent || parent == PvmParentNotSet) {
        /* Processus pere */
        int nchildren, children[MAXCHILDREN];
        int i, j, res, rc;
      int bytes, tag, from_tid;


    /* Ask PVM for information about the virtual machine, and display
       it to the user. 
    */
    
        pvm_config(&nhost, &narch, &hostp);
        printf("I found the following %d hosts...\n",nhost);
		
		
        for (i = 0; i < nhost; i++)
          printf("%d. %s \t(%s)\n",i,hostp[i].hi_name,hostp[i].hi_arch);



        rc = pvm_spawn("pvm_mandel", NULL, PvmTaskDefault, NULL, 
                       NUMCHILDREN, children);
        if(rc < 0) pvm_ferror("pvm_spawn", 1);
        printf("%d enfants\n", rc);
        nchildren = 0;
        for(i = 0; i < NUMCHILDREN; i++) {
            printf("Enfant %d, tid = %d\n", i, children[i]);
            if(children[i] >= 0)
                nchildren++;
            if(nchildren < 1)
                pvm_ferror("Pas d'enfants", 0);
        }
        for(i = -MAXX; i <= MAXX; i++) {
            for(j = -MAXY; j <= MAXY; j++) {
               rc = pvm_recv(-1,-1);

              if (rc < 0) {
                printf("An error occurred when trying to receive a message.\n");
                break;
              }

      /* Find out who this message is from, and how big it is. */

               rc = pvm_bufinfo(rc,&bytes,&tag,&from_tid);

               /* printf("received message from %s of %d bytes, tag %d\n",
                  get_host_by_tid(hostp,nhost,from_tid), bytes, tag);
               */

                rc = pvm_upkint(&res, 1, 1);
                if(rc < 0) pvm_ferror("pvm_upkint", 1);
                cases[i + MAXX][j + MAXY] = res;
            }
        }
        dump_ppm("mandel.ppm", cases);
        printf("Fini.\n");
        pvm_exit();
        exit(0);
    } else if(parent >= 0) {
        /* On est l'un des fils */
        double x, y;
        int i, j, res, rc;
        printf("Fils: %d\n", tid);
        for(i = -MAXX; i <= MAXX; i++) {
            for(j = -MAXY; j <= MAXY; j++) {
                x = 2 * i / (double)MAXX;
                y = 1.5 * j / (double)MAXY;
                res = mandel(x, y);
                rc = pvm_initsend(PvmDataDefault);
                if(rc < 0) pvm_ferror("pvm_initsend", 1);
                rc = pvm_pkint(&res, 1, 1);
                if(rc < 0) pvm_ferror("pvm_pkint", 1);
                rc = pvm_send(parent, 0);
                if(rc < 0) pvm_ferror("pvm_send", 1);
            }
        }
        printf("Fils %d termine.\n", tid);
        pvm_exit();
        exit(0);
    } else
        pvm_ferror("pvm_parent", 1);

    assert(0);
}

/* function to compute a point - the number of iterations 
plays a central role here */

int
mandel(double cx, double cy)
{
    int i;
    double zx, zy;
    zx = 0.0; zy = 0.0;
    for(i = 0; i < NBITER; i++) {
        double zxx = zx, zyy = zy;
        zx = zxx * zxx - zyy * zyy + cx;
        zy = 2 * zxx * zyy + cy;
        if(zx * zx + zy * zy > 4.0)
            return i;
    }
    return -1;
}

/* management of error messages */

static void
pvm_ferror(const char *string, int p)
{
    if(p)
        pvm_perror((char*)string);
    else
        fprintf(stderr, "%s\n", string);
    pvm_exit();
    exit(1);
}


/* the image commputer can be transformed in a ppm file so
   to be seen with xv */

int
dump_ppm(const char *filename, int valeurs[NX][NY])
{
    FILE *f;
    int i, j, rc;

    f = fopen(filename, "w");
    if(f == NULL) { perror("fopen"); exit(1); }
    fprintf(f, "P6\n");
    fprintf(f, "%d %d\n", NX, NY);
    fprintf(f, "%d\n", 255);
    for(j = NY - 1; j >= 0; j--) {
        for(i = 0; i < NX; i++) {
            unsigned char pixel[3];
            if(valeurs[i][j] < 0) {
                pixel[0] = pixel[1] = pixel[2] = 0;
            } else {
                unsigned char val = MIN(valeurs[i][j] * 12, 255);
                pixel[0] = val;
                pixel[1] = 0;
                pixel[2] = 255 - val;
            }
            rc = fwrite(pixel, 1, 3, f);
            if(rc < 0) { perror("fwrite"); exit(1); }
        }
    }
    fclose(f);
    return 0;
}
        
       /* Search the host information list for a hostname given a task ID. */

char *get_host_by_tid(struct pvmhostinfo *hostp, int nhost, int tid) {
  int j, host_tid;
  char *host_name = "unknown";
  host_tid = pvm_tidtohost(tid);
  for (j=0; j<nhost; j++)
    if (hostp[j].hi_tid == host_tid) {
      host_name = hostp[j].hi_name;
      break;
    }
  return host_name;
}
 
