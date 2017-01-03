#include<stdio.h>

int main()
{
  unsigned long long int vector, newSet;
  int t;
  short int n;
  char op;
  
  scanf("%d", &t);

  while(t--){

    scanf("%llu %hu", &vector, &n);

    while(n--){

      scanf("\n%c", &op);
      
      switch(op){
	
      case 'U':
	scanf("%llu", &newSet);
	vector |= (newSet);
	break;
	
      case 'I':
	scanf("%llu", &newSet);
	vector &= (newSet);
	break;
	
      case 'D':
	scanf("%llu", &newSet);
	vector &= ~(newSet);
	break;
	
      case 'C':
	vector = ~vector;
	break;
	
      default :
	break;
      }
    }
    printf("%llu\n", vector);
  }

  return (0);
}
