#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define N 36

inline unsigned int getRandomValue()
{
	return rand()*time(NULL);
}

int main()
{
	int i=0,j=0;
	char arr[N+1]={0};
	char ch='a';
	
	for(i=0;i<26;i++)
		arr[i]=(char)(ch+i);
	
	ch='0';
	for(j=0;j<10;  j++, i++)
		arr[i]= (char)(ch  + j);
		
	printf("Press \"Ctrl+c\" or q to quit\n");
	do
	{
		for(i=0;i<8;i++)
			printf("%c",arr[getRandomValue()%36]);
		printf("\n");
		scanf("%c",&ch);
	}
	while(ch != 'q' && ch!='Q');
	return 0;
}




