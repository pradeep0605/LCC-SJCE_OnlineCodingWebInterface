#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
  int t, n, k;
  int click_arr[1005], click_num, open_cnt = 0;
  char click_cmd[100];

  scanf("%d", &t);
  while(1){
    scanf("%d %d",&n, &k);
    memset (click_arr, 0, (1005*sizeof(int)) );
    while(k--)
      {
	scanf("%s", click_cmd);
	if(strcmp(click_cmd, "CLICK") == 0)
	  {
	    scanf("%d",&click_num);
	    if(click_arr[click_num]) {
	      click_arr[click_num] = 0;
	      open_cnt--;
	    }
	    else
	      {
		click_arr[click_num] = 1;
		open_cnt++;
	      }
	  }
	else 
	  {
	    memset (click_arr, 0, (1005*sizeof(int)) );
	    open_cnt = 0;
	  }
	printf("%d\n",open_cnt);
      }
  }
 // click_cmd[1000000]='\0';
  
  return 0;
}