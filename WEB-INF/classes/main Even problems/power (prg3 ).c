#include<stdio.h>

int main()
{
  unsigned short int t, count;
  unsigned long long int n, temp;

  scanf("%hu", &t);

  while(t--){
    scanf("%llu", &n);

    count = -1;
    while(n){
      n >>= 1;
      count++;
    }

    temp = (unsigned long long int)(1) <<count;
    printf("%llu %llu\n", temp, temp <<1);
  }

  return (0);
}
