#include<stdio.h>

int b[20][20],n,dist[20],path[20],count=0,visited[20],i,j,min;

void add(int y,int pos)					//addd function adds the node to the path array
{
	int k=count;
        path[count]=pos;
	path[count+1]=y;
	k+=2;
}


void tsp1(int pos)
{
	int y;

	visited[pos]=1;					//set node to visited

	if(count!=n)					//check the count is equal to no, of nodes or not, as function is recursive
	{
		min=500;
		for(j=0;j<n;j++)
		{
			if(b[pos][j]<min && j!=pos && visited[j]!=1)	//check the minimium distance, and node visited
			{
				min=b[pos][j];
				y=j;
			}
		}
		if(min!=500)					//if not satisfied than the distance between last traversed node to first node will be added
		{
			dist[count]=min;			// dist array will contain minimum distanced node of respective nodes
			add(y,pos);
			count++;
			tsp1(y);				//traversing will now start from next minimum distanced node(recursively)
		}
		else	
		{
			dist[count]=b[pos][0];			
		}
	}
	else
	{
		path[pos]=0;					//if we reaches to the last node than than we have to go to initial or starting node
	}
}




int main()
{

printf("Enter no. of cities\n");
scanf("%d",&n);
printf("Enter distances between the cities\n");

for(i=0;i<n;i++)
	for(j=0;j<n;j++)
		scanf("%d",&b[i][j]);

tsp1(0);

printf("\nPath = ");

for(i=0;i<=n;i++)
	printf("%d->",path[i]);					//printing minimum path traversed

int sum=0;

for(i=0;i<n;i++)
	sum=sum+dist[i];					//calculating distance covered for minimum path

printf("\nDistance = %d\n",sum);

return 0;

}
