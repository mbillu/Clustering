public static int deg(int v1,int[][] v,int n)
	{
		int i,d=0;
		for(i=1;i<=n;i++)
		   if(v[v1][i]==1)
			d++;
		return d;
	}
	
	
	public static void tower(int[] medoid,int[] tempclusters,int[][] a,int [][] v,int n,int k)
	{
		double[][] b=new double[n+1][n+1];
		int points[][]=new int[n+1][3];
		double d=0.0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter x and y coordinates of every node");
		
		/*medoid[1]=1;
		medoid[2]=5;
		medoid[3]=10;*/
		
		for(int i=1;i<=n;i++)
		{
			points[i][0]=scan.nextInt();
			points[i][1]=scan.nextInt();
		}
		
		for(int j=1;j<=n;j++)
		{
			for(int i=1;i<=n-1;i++)
			{
				d=Math.ceil(Math.sqrt(Math.pow(points[i+1][0]-points[j][0],2)+Math.pow(points[i+1][1]-points[j][1],2)));
				b[j][i+1]=d;
				b[i+1][j]=d;
			}
		}
		
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				System.out.print(b[i][j]+" ");
			}
			System.out.println();
		}
		
		double min=10000.0,max=0.0;
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				if(b[i][j]>max)
					max=b[i][j];
				if(min>b[i][j])
					min=b[i][j];
			}
		}
		
		int range=(int)((min+max)/2);
		int[] degree=new int[k+1];
		
		for(int i=1;i<=k;i++)
		{
			degree[i]=deg(medoid[i],v,n);
		}
		
		int max1=degree[0],index=1;
		
		for(int i=1;i<=k;i++)
		{
			if(degree[i]>max)
			{
				max1=degree[i];
				index=i;
			}
		}
		
		int[] tower=new int[k+1];
		tower[1]=medoid[index];
		
		for(int i=2;i<=k;i++)
		{
			for(int j=1;j<=k;j++)
			{
				if(b[tower[j]][medoid[i]]<=range)
				{
					tower[i]=medoid[i];
					break;
				}
				else
				{
					for(int l=1;l<=n;l++)
					{
						if(tempclusters[l]==medoid[i])
						{
							if(b[tower[j]][l]<=range)
							{
								tower[i]=l;
								break;
							}
						}
					}
				}
			}
		}
		
		for(int i=1;i<=k;i++)
		{
			System.out.println(tower[i]+" ");
		}
	}
