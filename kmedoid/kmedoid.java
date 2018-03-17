import java.util.*;


public class kmedoid
{

	public static void kme(int[][] a,int[][] v,int n,int k)
	{
		
       		int[] medoid=new int[k+1];
       		int[] nonmedoid=new int[n-k+1];
       		int[] initialmedoid=new int[k+1];
       		int temp,flag=0;
       		
       		Scanner scan = new Scanner(System.in);
       		System.out.println("enter three initial centers");
       		
       		initialmedoid[1]=scan.nextInt();
       		initialmedoid[2]=scan.nextInt();
     		initialmedoid[3]=scan.nextInt();
     		
     		medoid[1]=initialmedoid[1];
     		medoid[2]=initialmedoid[2];
     		medoid[3]=initialmedoid[3];
     		
       		
       		
       		int[] clusters=new int[n+1];
       		int[] tempclusters=new int[n+1];
		int[] distances=new int[k+1];
		int[][] distances1=new int[n+1][k+1];
		int min=1000;
		int index=0;
       		
       		for(int i=1;i<=n;i++)
		{
		    
		    Arrays.fill(distances,0);
		    min=1000;
		    

		    for(int j=1;j<=k;j++)
		    {
			distances[j]=a[medoid[j]][i];
			if(distances[j]<min)
			{
			    min=distances[j];
			    index=j;
			    clusters[i]=medoid[index];
			    
			}
		    }
		    distances1[i][index]=min;
		   
		}
				
			System.out.println("Initial clusters, centers as "+medoid[1]+" "+medoid[2]+" "+medoid[3]+" are");
			
			for(int i=1;i<=k;i++)
			{
				System.out.print(medoid[i]+"  ( ");
				for(int j=1;j<=n;j++)
				{
					if(clusters[j]==medoid[i])
					{
						System.out.print(j+" ");
					}
				}
				System.out.print(")");
				System.out.println();
			}
			
			
		
				
		
       			
       			
       			
		int[] sum=new int[k+1];
		int[] sum1=new int[k+1];
		
		for(int i=1;i<=k;i++)
		{
			int add=0;
			for(int j=1;j<=n;j++)
			{
				add+=distances1[j][i];
			}
			sum[i]=add;
			//sum1[i]=add;
		}
		
		
		while(flag==0)
       		{
			int x=1;
			for(int i=1;i<=n;i++)
			{
				if(i!=medoid[1] && i!=medoid[2] && i!=medoid[3])
				{
					nonmedoid[x]=i;
					x++;
				}
			}
			
			int[][] distances2=new int[n+1][k+1];
			int[] tempmedoid=new int[k+1];
			
			initialmedoid[1]=medoid[1];
	       		initialmedoid[2]=medoid[2];
	       		initialmedoid[3]=medoid[3];
			
			tempmedoid[1]=medoid[1];
	       		tempmedoid[2]=medoid[2];
	       		tempmedoid[3]=medoid[3];
			//int[] sum2=new int[k+1];
			
			
			for(int o=1;o<=k;o++)
			{
				for(int i=1;i<=n;i++)
				{
					
					for(int j=1;j<=k;j++)
					{
						sum1[j]=0;
						distances2[i][j]=0;
					}
				}
				
				for(int m=1;m<=n-k;m++)
				{
					
					tempmedoid[o]=nonmedoid[m];
					//Arrays.fill(clusters,0);
					//System.out.print(tempmedoid[o]+" ");
					
					min=1000;
					index=0;
					
					for(int i=1;i<=n;i++)
					{
					    
					    Arrays.fill(distances,0);
					   // Arrays.fill(clusters,0);
					    min=1000;
					    

					    for(int j=1;j<=k;j++)
					    {
						distances[j]=a[tempmedoid[j]][i];
						if(distances[j]<min)
						{
						    min=distances[j];
						    index=j;
						    clusters[i]=tempmedoid[index];
						    
						}
					    }
					    distances2[i][index]=min;
					   
					}
							
					
					
					for(int i=1;i<=k;i++)
					{
						int add=0;
						for(int j=1;j<=n;j++)
						{
							add+=distances2[j][i];
						}
						sum1[i]=add;
					}
					
					/*for(int i=1;i<=k;i++)
						System.out.print(sum1[i]+" ");
						System.out.println();*/
						
					
					if(sum[o]>sum1[o])
					{	
						sum[o]=sum1[o];
						tempmedoid[o]=nonmedoid[m];
						temp=nonmedoid[m];
						nonmedoid[m]=medoid[o];
						medoid[o]=temp;
						
						
						for(int i=1;i<=n;i++)
							tempclusters[i]=clusters[i];
						
						
					}
					else
					{
						tempmedoid[o]=medoid[o];
						sum[o]=sum[o];
						medoid[o]=medoid[o];
					}
					
						
				}
			}
			
			int count=0;
			for(int i=1;i<=k;i++)
			{
				
				if(medoid[i]==initialmedoid[i])
				{
					count++;
				}
			}
			
			if(count==k)
				flag=1;
			
			
		}
		
		System.out.println();
		System.out.println("After applying KMedoid algorithm, centers are "+medoid[1]+" "+medoid[2]+" "+medoid[3]+" are");
			
		for(int i=1;i<=k;i++)
		{
			System.out.print(medoid[i]+"  ( ");
			for(int j=1;j<=n;j++)
			{
				if(tempclusters[j]==medoid[i])
				{
					System.out.print(j+" ");
				}
			}
			System.out.print(")");
			System.out.println();
		}
		
	}
	
	
	
	
	public static void main(String args[])
	{	
	    Scanner scan = new Scanner(System.in);
	    
	    int n,i,j,k;
	    
	    System.out.println("enter number of vertices");
	    n=scan.nextInt();
	    
	    int[][] a=new int[n+1][n+1];
	    int[][] v=new int[n+1][n+1];

	    System.out.println("enter adjacency matrix\n");
	
	    for(i=1;i<=n;i++)
	    {
		for(j=1;j<=n;j++)
		    v[i][j]=scan.nextInt();
	    }

	    for(i=1;i<=n;i++)
	    {
		for(j=1;j<=n;j++)
		{
		    if(v[i][j]==0)
			a[i][j]=99999;
		    else
			a[i][j]=v[i][j];
		}
	    }
	    

	    for(i=1;i<=n;i++)
	    {
		for(j=1;j<=n;j++)
		{
		    for(k=1;k<=n;k++)
		    {
			if(a[j][k]>a[j][i]+a[i][k])
			    {
			 if(j==k)
			    a[j][k]=0;
			    else
			    a[j][k]=a[j][i]+a[i][k];
			}
		    }
		}
	    }
	    
	    System.out.println("\nenter no. of clusters");
	    int c=scan.nextInt();
	 
	 kme(a,v,n,c);
	 
	 }


}
