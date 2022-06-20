import java.util.Scanner;
public class Bankers {
	private int need[][],allocate[][],max[][],avail[],numP,numR;

	private void input()
	{
		Scanner sc=new Scanner(System.in);



		System.out.print("Enter no. of processes and resources : ");
		numP=sc.nextInt();  //# of process
		numR=sc.nextInt();  //# of resources

		//프로세스의 need,max,allocate는 [프로세스의 넘버][개수]를 이용해야 하기 때문에 2차원 배열을 이용하여 줍니다.
		need=new int[numP][numR];  //initializing arrays
		max=new int[numP][numR];
		allocate=new int[numP][numR];
		avail=new int[numR];

		System.out.println("Enter allocation matrix -->");
		for(int i=0;i<numP;i++)
			for(int j=0;j<numR;j++)
				allocate[i][j]=sc.nextInt();  //allocation matrix

		System.out.println("Enter max matrix -->");
		for(int i=0;i<numP;i++)
			for(int j=0;j<numR;j++)
				max[i][j]=sc.nextInt();  //max matrix

		System.out.println("Enter available matrix -->");
		for(int j=0;j<numR;j++)
			avail[j]=sc.nextInt();  //available matrix

		sc.close();
	}

	private int[][] calc_need(){
		for(int i=0;i<numP;i++)
			for(int j=0;j<numR;j++)  //calculating need matrix
				need[i][j]=max[i][j]-allocate[i][j];

		return need;
	}

	private boolean check(int i){
		//checking if all resources for ith process can be allocated
		for(int j=0;j<numR;j++) 
			if(avail[j]<need[i][j])
				return false;

		return true;
	}
	
	
	public void isSafe()
	{
		input();
		calc_need();
		int list[]=new int[numP];
		for(int i=0; i<numP; i++){
				list[i]=1;
		}


		int count_num=0;

		//반복하는 횟수가 많아봤자 프로세스의 개수^2까지만 하면 SAFETY되는지 안되는지 결과가 나옵니다.
		for(int j=0; j<numP; j++)
		{
			for(int i=0; i<numP; i++)
			{
			
				if(list[i]==1 && check(i)==true)
				{ //finish[false]이고 avail>=need 일때만
					for(int k=0; k<numR; k++) 
					{
						avail[k]+=allocate[i][k];
						
					}
					
					list[i]=0;count_num+=1;
					System.out.println("allocated process:"+i);
				
				}
			}
		
			
		}
		if(count_num==numP)
			System.out.println("safely allocated");
		else
			System.out.println("all process cant be allocated safely");
		
		}
	public static void main(String[] args) {
		new Bankers().isSafe();
		
		
	
	}

}

