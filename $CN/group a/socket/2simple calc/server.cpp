#include<stdio.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<string.h>
#include<arpa/inet.h>
#include <math.h>
int main()
{
	int welcomeSocket,newSocket;
	char buffer[20];
	int ch;
	double res;
        int nos[2];
	double angle, sine;

	struct sockaddr_in serverAddr;
	struct sockaddr_storage serverStorage;
	socklen_t addr_size;
	// create socket 
	welcomeSocket = socket(PF_INET,SOCK_STREAM,0);

		// construct Ip Address 
	serverAddr.sin_family=AF_INET;
	serverAddr.sin_port=htons(7895);
	serverAddr.sin_addr.s_addr=inet_addr("127.0.0.1");

	memset(serverAddr.sin_zero,'\0',sizeof serverAddr.sin_zero);
	bind(welcomeSocket,(struct sockaddr *) &serverAddr,sizeof(serverAddr));
	if(listen(welcomeSocket,5)==0)
		printf("Listening\n");
	else
		printf("Error\n");
	addr_size = sizeof serverStorage;
	
	newSocket = accept(welcomeSocket,(struct sockaddr *) &serverStorage, &addr_size);
		

	recv(newSocket,nos,100,0);
	
       recv(newSocket,&ch,10,0);
	printf("choice received: %d\n",ch);
	//printf("Data received: %d\n",buffer[1]);
        //strcpy(buffer,"Enter two nos:\n");
       // send(newSocket,buffer,17,0);
	
	printf("Data received: %d\n",nos[0]);
	printf("Data received: %d\n",nos[1]);	
	switch(ch)
	{
	case 1: res=nos[0]+nos[1];
		break;
	case 2: res=nos[0]-nos[1];
		break;

	case 3: res=nos[0]/nos[1];
		break;

	case 4: res=nos[0]*nos[1];
		break;
	}
	
	send(newSocket,&res,50,0);
   
return 0;
}

	
