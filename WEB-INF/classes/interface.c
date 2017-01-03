#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(int n,char **args)
{
	
	char arr1[100]= {' ', '>'};
	char arr2[100]= {' ','<'};
	char arr3[100]= {0};
	if(n==4)
	{
		strcpy(arr3,args[1]);
	 	system( strcat(strcat(arr3," "), strcat(strcat(arr2,args[2]) , strcat(arr1,args[3])))   );
	}
	return 0;
}

