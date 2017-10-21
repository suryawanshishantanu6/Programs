#include<stdio.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<string.h>
#include<arpa/inet.h>
#include<math.h>
int main()
{
	int clientSocket;
	char buffer[30];
	int n1;
	double res;
	int nos[2];
	struct sockaddr_in serverAddr;
	socklen_t addr_size;

	clientSocket =socket(PF_INET,SOCK_STREAM,0);
	serverAddr.sin_family=AF_INET;
	serverAddr.sin_port =htons(7895);
	serverAddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	memset(serverAddr.sin_zero,'\0',sizeof serverAddr.sin_zero);
	
	addr_size= sizeof serverAddr;
	connect(clientSocket, (struct sockaddr *) &serverAddr,addr_size);
	/*recv(clientSocket,buffer,1024,0);*/

	
	
	//send(clientSocket,&n1,100,0);
	

	//printf("%s\n",buffer);
	


	printf("enter angle ");
	scanf("%d",&nos[0]);
	send(clientSocket,nos,100,0);	
	printf("\n1... Sin\n2... Cos\n3.. Tan\n4.. Cot\n5.. Sec \n6.. Cosec\n7.. Tan\nEnter your choice");
        scanf("%d",&n1);
	send(clientSocket,&n1,100,0);
	
	recv(clientSocket,&res,100,0);
	printf("=%f",res);
	
return 0;
}
