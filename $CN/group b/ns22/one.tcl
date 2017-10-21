set ns [new Simulator]

#Define different colors for data flows (for NAM)
$ns color 1 Blue


#Open the Trace files
set file1 [open out.tr w]
$ns trace-all $file1

#Open the NAM trace file
set file2 [open out.nam w]
$ns namtrace-all $file2

#Define a 'finish' procedure
proc finish {} {
        global ns file1 file2
        $ns flush-trace
        close $file1
        close $file2
       # exec ./nam out.nam &
        exit 0
}


#Create six nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]


$ns at 0.2 "$n0 label \"FTP\""

#Create links between the nodes
$ns duplex-link $n0 $n1 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail

#Give node position 
$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up



#Setup a TCP connection
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n2 $sink
$ns connect $tcp $sink
$tcp set fid_ 1
$tcp set window_ 8000
$tcp set packetSize_ 552


#Setup a FTP over TCP connection
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP



$ns at 0.30 "$ftp start"
$ns at 2.0 "$ftp stop"






$ns at 2.0 "finish"
$ns run
