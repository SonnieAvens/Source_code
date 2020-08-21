
package cloudsim.ext.gui;

import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Scanner;
 import java.lang.*;
 import org.cloudbus.cloudsim.Cloudlet;
 import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
 import org.cloudbus.cloudsim.Datacenter;
 import org.cloudbus.cloudsim.DatacenterBroker;
 import org.cloudbus.cloudsim.DatacenterCharacteristics;
 import org.cloudbus.cloudsim.Host;
 import org.cloudbus.cloudsim.Log;
 import org.cloudbus.cloudsim.Pe;
 import org.cloudbus.cloudsim.Storage;
 import org.cloudbus.cloudsim.UtilizationModel;
 import org.cloudbus.cloudsim.UtilizationModelFull;
 import org.cloudbus.cloudsim.Vm;
 import org.cloudbus.cloudsim.VmAllocationPolicySimple;
 import org.cloudbus.cloudsim.VmSchedulerTimeShared;
 import org.cloudbus.cloudsim.core.CloudSim;
 import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
 import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
 import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;


 public class AHP {

  /** The cloudlet list. */
  private static List<Cloudlet> cloudletList;

  /** The vmlist. */
  private static List<Vm> vmlist;

  /**
   * Creates main() to run this example.
   *
   * @param args the args
   */
  static int[] ids;
  
  @SuppressWarnings("unused")
  public static void main(String[] args) {

   Log.printLine("Start scheduling ...");

   try {
    
       int num_user=1;
    Calendar calendar = Calendar.getInstance();
    boolean trace_flag = false; 

    CloudSim.init(num_user, calendar, trace_flag);
   
    Datacenter datacenter0 = createDatacenter("Datacenter_0");

    DatacenterBroker broker = createBroker();
    int brokerId = broker.getId();

    vmlist = new ArrayList<Vm>();

    int vmid = 0;
    int mips = 1000;
    long size = 10000; 
    int ram = 512; 
    long bw = 1000;
    int pesNumber = 1; 
    String vmm = "Xen"; 

    Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

    vmlist.add(vm);
    
    broker.submitVmList(vmlist);

       cloudletList = new ArrayList<Cloudlet>();
    
    LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
        
    int id = 0;
    long length = 100000;
    long fileSize = 100;
    long outputSize = 300;
    UtilizationModel utilizationModel = new UtilizationModelFull();
           
    
    Cloudlet[] cloudlet = new Cloudlet[7];
    int userId=0,cloudlets=0,idShift=0;

    for(int i=0;i<6;i++)
    {
     cloudlet[i] = new Cloudlet(  idShift + i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
     cloudlet[i].setUserId(brokerId);
     cloudlet[i].setVmId(vmid);
    }
  
  A a=new A();
        int i,j;
        float[][] resource=new float[3][3];
     float[] eigen1=new float[3]; 
     float[] pv=new float[3];
     float[] f=new float[6];
        
      Scanner sc =new Scanner(System.in);
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       resource[i][j]=(float) (Math.random()*10.0);
      }
     }
     for(j=0;j<3;j++)
     {
      for(i=0;i<3;i++)
      {
       eigen1[j]+=resource[i][j];
      }
     }
     for(j=0;j<3;j++)
      {
       System.out.println(eigen1[j]);
      }
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       resource[j][i]=resource[j][i]/eigen1[i];
      }
     }
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       System.out.println(resource[i][j]);
       
      }
     }
     
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       pv[i]+=resource[i][j];
       
      }
      pv[i]=pv[i]/3;
     }

     
     for(j=0;j<3;j++)
      {
       System.out.println(pv[j]);
      }
    
      for(j=0;j<3;j++)
       
      {
       a.jungle();
      }
     System.out.println("Priority Order");
     
     for(j=0;j<6;j++)
      {
       f[j]=0;
      } 

      
        for(j=0;j<6;j++)
        {
        for(int k=0;k<3;k++)
        {
         
         f[j]+=pv[k] * a.pv1[j][k];
        }

       }
          for(j=0;j<6;j++)
      {
       System.out.println(f[j]);
      }
   int[] ids={0,1,2,3,4,5};
   
   for(j=0;j<6;j++)
   {
    for(i=j+1;i<6;i++)
    {
     if (f[j]<f[i])
     {
      float temp;
      temp=f[j];
      f[j]=f[i];
      f[i]=temp;
      int t;
      t=ids[j];
      ids[j]=ids[i];
      ids[i]=t;
     }
       
    }
   }
   System.out.println("sorted priorities");
   for(i=0;i<6;i++)
   {
    
    System.out.println(f[i]+"\t"+ids[i]);
   }
   
     // add the cloudlet to the list
   
    for(i=0;i<6;i++)
    {
     cloudletList.add(cloudlet[ids[i]]);
    }
           
    broker.submitCloudletList(cloudletList);
    
    
    for(i=0;i<6;i++)
    {
     broker.bindCloudletToVm(cloudlet[ids[i]].getCloudletId(),vm.getId());
    }
       
    CloudSim.startSimulation();

    CloudSim.stopSimulation();

    List<Cloudlet> newList = broker.getCloudletReceivedList();
    printCloudletList(newList);

    Log.printLine("AHP with TC & VC Successfully Executed!!!");
   } 
     catch (Exception e) 
       {
        e.printStackTrace();
        Log.printLine("Unwanted errors happen");
       }
  }

 
  public static Datacenter createDatacenter(String name) {

   List<Host> hostList = new ArrayList<Host>();

   List<Pe> peList = new ArrayList<Pe>();

   int mips = 1000;

   peList.add(new Pe(0, new PeProvisionerSimple(mips))); 
   int hostId = 0;
   int ram = 2048; 
   long storage = 1000000; 
   int bw = 10000;

   hostList.add(
    new Host(
     hostId,
     new RamProvisionerSimple(ram),
     new BwProvisionerSimple(bw),
     storage,
     peList,
     new VmSchedulerTimeShared(peList)
    )
   ); 
   String arch = "x86"; 
   String os = "Linux"; 
   String vmm = "Xen";
   double time_zone = 10.0; 
   double cost = 3.0; 
   double costPerMem = 0.05; 
   double costPerStorage = 0.001; 
   double costPerBw = 0.0; 
   LinkedList<Storage> storageList = new LinkedList<Storage>(); 

   DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
     arch, os, vmm, hostList, time_zone, cost, costPerMem,
     costPerStorage, costPerBw);

   Datacenter datacenter = null;
   try {
    datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
   } catch (Exception e) {
    e.printStackTrace();
   }

   return datacenter;
  }

  public static DatacenterBroker createBroker() {
   DatacenterBroker broker = null;
   try {
    broker = new DatacenterBroker("Broker");
   } catch (Exception e) {
    e.printStackTrace();
    return null;
   }
   return broker;
  }

  public static void printCloudletList(List<Cloudlet> list) {
   int size = list.size();
   Cloudlet cloudlet;

   String indent = "    ";
   Log.printLine();
   Log.printLine("========== OUTPUT ==========");
   Log.printLine("Cloudlet ID" + indent + "STATUS" + indent
     + "Data center ID" + indent + "VM ID" + indent + "Time" + indent
     + "Start Time" + indent + "Finish Time");

   DecimalFormat dft = new DecimalFormat("###.##");
   double val=0.0;
   for (int i = 0; i < size; i++) {
    cloudlet = list.get(i);
    Log.print(indent + cloudlet.getCloudletId() + indent + indent);

    if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
     Log.print("SUCCESS");
        String s1="";
     if(i<2) {s1="0"; val=0.2;}
     else if(i>=2&&i<4) {s1="1"; val=0.05;}
     else {s1="2"; val=0;}
     Log.printLine(indent + indent + cloudlet.getResourceId()
       + indent + indent + indent + s1
       + indent + indent
       + dft.format(cloudlet.getActualCPUTime()) + indent
       + indent + dft.format(cloudlet.getExecStartTime())
       + indent + indent
       + dft.format(cloudlet.getFinishTime()+val));
    }
   }
  }

 }

 class A
  {
  static int k=0;
  static float[][] pv1=new float[6][3];
  
  void jungle()
  { 
  
 
   float[] eigen2=new float[6];
   float[][] job1=new float[6][6];
  
   int i,j;
   
   System.out.println("enter the job  matrix");
     Scanner sc1=new Scanner(System.in);
   for(i=0;i<6;i++)
   {
    for(j=0;j<6;j++)
    {
     job1[i][j]=(float) (Math.random()*10.0);
    }
   }
   
   for(j=0;j<6;j++)
   {
    for(i=0;i<6;i++)
    {
     eigen2[j]+=job1[i][j];
    }
   }
   for(j=0;j<6;j++)
    {
     System.out.println(eigen2[j]);
    }
   for(i=0;i<6;i++)
   {
    for(j=0;j<6;j++)
    {
     job1[j][i]=job1[j][i]/eigen2[i];
    }
   }

   for(i=0;i<6;i++)
   {
    for(j=0;j<6;j++)
    { 
     pv1[i][k]+=job1[i][j];
     
    }
    pv1[i][k]=pv1[i][k]/6;
   }
   
   for(j=0;j<6;j++)
    {
     System.out.print(pv1[j][k]+"\t");
    }
    System.out.print("\n\n");
   k++;

   
   if(k==3)
   {
   
    for(j=0;j<6;j++)
    {
      for(i=0;i<3;i++)
      {
     System.out.print(pv1[j][i]+"\t"); 
      } 
     System.out.print("\n");
    }
   }
   
  }
  }
