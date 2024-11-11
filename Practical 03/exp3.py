import ipaddress 

def subnet_calculator(network_address, new_prefix_length): 
    try: 
        # Create an IPv4 network object 
        network = ipaddress.IPv4Network(network_address, strict=False) 

        # Display information about the network 
        print(f"\nNetwork Address: {network.network_address}") 
        print(f"Broadcast Address: {network.broadcast_address}") 
        print(f"Default Subnet Mask: {network.netmask}") 
        print(f"Total IPs in the Network: {network.num_addresses}\n") 

        # Subnet the network 
        print(f"Subnets after applying /{new_prefix_length}:") 
        subnets = list(network.subnets(new_prefix=new_prefix_length)) 
        for subnet in subnets: 
            print(f"Subnet: {subnet.network_address}, Broadcast: {subnet.broadcast_address}, Total IPs: {subnet.num_addresses}") 

    except ValueError: 
        print("Invalid network address!") 

# Example: Class C IP 192.168.1.0/24 subnetted to /26 
network_address = input("Enter the network address (e.g., 192.168.1.0/24): ") 
new_prefix_length = int(input("Enter the new prefix length (e.g., 26 for /26): ")) 
subnet_calculator(network_address, new_prefix_length)
