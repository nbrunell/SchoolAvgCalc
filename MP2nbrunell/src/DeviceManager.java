import java.util.ArrayList;
import java.util.Scanner;

public class DeviceManager {
    private static ArrayList<Device> devices = new ArrayList<>();

    public static void main(String[] args) 
    {
        initializeDevices();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice)
            {
                case 1:
                    displayDevices();        
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    addDevice(scanner);
                    break;
                case 3:
                    editDevice(scanner);
                    break;
                case 4:
                    searchDevices(scanner);
                    break;
                case 5:
                    checkOutDevice(scanner);
                    break;
                case 6:
                    checkInDevice(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    private static void initializeDevices() 
    {
        devices.add(new Device("D12345", "iPad Pro", true));
        devices.add(new Device("OP8888", "Kindle Oasis", true));
        devices.add(new Device("QRS82", "Samsung Galaxy Tab", true));
        devices.add(new Device("D17727", "iPad Mini", true));
        devices.add(new Device("KOXX2", "Kobo Clara HD", true));
    }

    private static void displayMenu() 
    {
        System.out.println("\n\n\n\t\t\tLibrary Device Checkout System\n");
        System.out.println("1. List devices by Title");
        System.out.println("2. Add New Devices");
        System.out.println("3. Edit Device Information");
        System.out.println("4. Search by Device Name");
        System.out.println("5. Check Out Devices");
        System.out.println("6. Check In Devices");
        System.out.println("7. Exit");
        System.out.print("\n\nSelect menu options 1-7: ");
    }	

    private static void displayDevices()
    {
        System.out.println("\n\n\n\n\t\tLibrary Device Checkout System - List\n");
        System.out.println(String.format("%-1s %-9s %-30s ", "#", "SKU", "Name"));
        int count = 1;
        for (Device device : devices) 
        {
            System.out.println(String.format("%d %s", count++, device));
        }
;
    }

    private static void addDevice(Scanner scanner) 
    {
    	System.out.println("\n\n\n\n\t\tLibrary Device Checkout System - Add New Device");
        System.out.print("Enter SKU: ");
        String sku = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        devices.add(new Device(sku.toUpperCase(), name, true));
        System.out.printf("\n\nAdded %s to Catalog.", name);
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void editDevice(Scanner scanner) {
        displayDevices();
        System.out.print("\nEnter Device SKU to edit: ");
        String sku = scanner.next();
        scanner.nextLine();  
        boolean found = false;

        for (Device device : devices) {
            if (device.getSku().equalsIgnoreCase(sku)) {
                System.out.print("Enter new SKU: ");
                String newSku = scanner.nextLine();
                device.setSku(newSku);

                System.out.print("Enter new Name: ");
                String newName = scanner.nextLine();
                device.setName(newName);

                System.out.println("Device updated successfully!");
                found = true;
                break; 
            }
        }

        if (!found) 
        {
            System.out.println("\nDevice not found.");
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }


    private static void searchDevices(Scanner scanner)
    {
    	System.out.println("\n\n\n\n\t\tLibrary Device Checkout System - Search");
        System.out.print("\nEnter Device to search for: ");
        String searchName = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        System.out.printf("\nListings for '%s'\n", searchName);
        System.out.println(String.format("%-3s %-10s %-30s",  "#", "SKU", "Name"));
     
        
        for (int i = 0; i < devices.size(); i++) 
        {
            Device device = devices.get(i);
            if (device.getName().toLowerCase().contains(searchName)) 
            {
                System.out.println(String.format("%-3d %-10s %-30s", i + 1, device.getSku(), device.getName()));
                found = true;
            }
        }	
        
        if (!found) 
        {
            System.out.println("No devices found with the given name.");
        }
    }

    private static void checkOutDevice(Scanner scanner) 
    {
    	System.out.println("\n\n\n\n\t\tLibrary Device Checkout System - Check Out Device");
        System.out.println("\nAvailable Devices:");
        System.out.println(String.format("%-3s %-10s %-30s", "#", "SKU", "Name"));

        
        int displayedCount = 0;
        for (int i = 0; i < devices.size(); i++) 
        {
            if (devices.get(i).getAvailable()) 
            {
                System.out.println(String.format("%-3d %-10s %-30s", i + 1, devices.get(i).getSku(), devices.get(i).getName()));
                displayedCount++;
            }
        }

        if (displayedCount == 0) 
        {  
            System.out.println("No available devices.");
            return;
        }

        
        System.out.print("\nEnter device number: ");
        int deviceNumber = scanner.nextInt();  
        scanner.nextLine();  

        
        if (deviceNumber > 0 && deviceNumber <= devices.size() && devices.get(deviceNumber - 1).getAvailable()) 
        {
            devices.get(deviceNumber - 1).setAvailable(false);
            System.out.println("Device Checked Out.");
        } 
        else 
        {
            System.out.println("\nInvalid device number or device not available.");
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void checkInDevice(Scanner scanner)
    {
    	System.out.println("\n\n\n\n\t\tLibrary Device Checkout System - Check Out Device");
        System.out.println("\nChecked Out Devices\n");
        System.out.println(String.format("%-3s %-10s %-30s", "#", "SKU", "Name"));

        int displayedCount = 0;
        for (int i = 0; i < devices.size(); i++) 
        {
            if (!devices.get(i).getAvailable()) 
            {
                System.out.println(String.format("%-3d %-10s %-30s", i + 1, devices.get(i).getSku(), devices.get(i).getName()));
                displayedCount++;
            }
        }

        if (displayedCount == 0) 
        {  
            System.out.println("No checked-out devices.");
            return;
        }
        
        System.out.print("\nEnter device number: ");
        int deviceNumber = scanner.nextInt();  
        scanner.nextLine(); 
        
        if (deviceNumber > 0 && deviceNumber <= devices.size() && !devices.get(deviceNumber - 1).getAvailable()) 
        {
            devices.get(deviceNumber - 1).setAvailable(true);
            System.out.println("Device Checked In.");
        } 
        else 
        {
            System.out.println("\nInvalid device number or device not available.");
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

}
