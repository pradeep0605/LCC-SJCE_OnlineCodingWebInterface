#include<stdio.h>
#include<stdlib.h>

int main()
{
  int t, N, n, **a, **m, i, j, left, right;

  scanf("%d", &t);

  a = (int **)calloc(1, sizeof(int *));
  m = (int **)calloc(1, sizeof(int *));

  N = 1;
  while(t--){
    scanf("%d", &n);

    if(N < n){
      a = (int **)realloc(a, n*sizeof(int *));
      m = (int **)realloc(a, n*sizeof(int *));
      N = n;
    }
    
    for(i = 0; i < n-1; i++){
      a[i] = (int *)realloc(a[i], (i+1)*sizeof(int));
      m[i] = (int *)realloc(m[i], (i+1)*sizeof(int));
      for(j = 0; j <= i; j++)
	scanf("%d", &a[i][j]);
    }

    a[i] = (int *)realloc(a[i], (i+1)*sizeof(int));
    m[i] = (int *)realloc(m[i], (i+1)*sizeof(int));
    for(j = 0; j <= i; j++){
      scanf("%d", &a[i][j]);
      m[i][j] = a[i][j];
    }

    for(i = n-2; i >= 0; i--){
      for(j = 0; j <= i; j++){
	left = m[i+1][j] + a[i][j];
	right = m[i+1][j+1] + a[i][j];
	m[i][j] = ((left >= right) ? left : right);
      }
    }

    printf("%d\n", m[0][0]);
    /*
    for(i = 0; i < n; i++){
      for(j = 0; j <= i; j++)
	printf("%d ", m[i][j]);
      printf("\n");
    }
    */
  }

  return (0);
}
