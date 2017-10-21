import socket

print 'Welcome to DNS Lookup'

var=raw_input('Enter URL/IP')
addr1 = socket.gethostbyname(var)
addr6=socket.gethostbyaddr(var)
print(addr1)
print(addr6)
