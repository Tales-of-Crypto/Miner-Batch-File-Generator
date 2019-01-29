/******************************************************************************************************************
Author: @talesofcrypto
Date: 1/22/2019
Purpose: A tool for generating batch files used to mine cryptocurrencies
*******************************************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;

public class MinerBatchFileGenerator extends JFrame
{
   private JLabel LBLCryptoChoice, LBLGPUType, LBLMinerChoice, LBLServerLocation, LBLWalletAddress, LBLNickName, lblimage, LBLDonate, LBLETH, LBLBTC, LBLMiningPools;
   private JTextField TFWalletAddress, TFMinerNickName, TFETH, TFBTC;
   private JButton BTNGenerateBatchFile, BTNDonate;
   private JPanel pnlTop, pnlBottom;
   private JComboBox JCBMining, JCBMinerUsed, JCBMiningPools;
   private String[] MiningChoices = {"","Bitcoin Private (BTCP)","Callisto (CLO)", "Ethereum (ETH)", "Ethereum Classic (ETC)", "Ravencoin (RVN)","ZCash (ZEC)","ZClassic (ZCL)", "Zencash (ZEN)"};
   private String[] MiningProgram = {"","Claymore Miner", "ETHMiner", "Phoenix Miner", "EWBF's ZCash Miner", "CCMiner", "SGMiner"};
   private String[] MiningPools = {"","Ethermine", "2Miners", "Crypto Pool Party"};
   private JRadioButton RBAMD, RBNvidia, RBAsia, RBEurope, RBUSA;
   private ButtonGroup TypeOfGPU, ServerLocation;
   private ImageIcon image;
  

 public MinerBatchFileGenerator()
   {
      setSize(520, 800); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Miner Batch File Generator");
      setLocationRelativeTo(null);
      buildTopPanel();
      buildBottomPanel();
      setLayout(new GridLayout(2,1));
      add(pnlTop);
      add(pnlBottom);
      setVisible(true);    
      setIconImage(image.getImage());
   }
   public void buildTopPanel()
   {
      pnlTop = new JPanel();
      LBLCryptoChoice = new JLabel("                                    What Would You Like To Mine?                                    ");
      JCBMining = new JComboBox(MiningChoices);
      LBLGPUType = new JLabel("                                                What Type of GPU are you Mining With?                                                ");
      RBAMD = new JRadioButton("AMD");
      RBNvidia = new JRadioButton("NVIDIA");
      TypeOfGPU = new ButtonGroup();
      TypeOfGPU.add(RBAMD);
      TypeOfGPU.add(RBNvidia);
      LBLMinerChoice = new JLabel("                                    What Miner Will You Be Using?                                    ");
      JCBMinerUsed = new JComboBox(MiningProgram);
      LBLMiningPools = new JLabel("                                                Choose your mining pool:                                                ");
      JCBMiningPools = new JComboBox(MiningPools);
      LBLServerLocation = new JLabel("                                                Please Select Your Mining Server:                                                ");
      RBAsia = new JRadioButton("Asia");
      RBEurope = new JRadioButton("Europe");
      RBUSA = new JRadioButton("USA");
      ServerLocation = new ButtonGroup();
      ServerLocation.add(RBAsia);
      ServerLocation.add(RBEurope);
      ServerLocation.add(RBUSA);
      LBLWalletAddress = new JLabel("                                    Wallet Address To Receive Payouts:                                    ");
      TFWalletAddress = new JTextField(29);
      LBLNickName = new JLabel("                                    Miner Nickname(Optional):                                    ");
      TFMinerNickName = new JTextField(25);
      BTNGenerateBatchFile = new JButton("      Generate Miner Batch File       ");
      BTNGenerateBatchFile.addActionListener(new CalcButtonListener());
      

      pnlTop.add(LBLCryptoChoice);
      pnlTop.add(JCBMining);
      pnlTop.add(LBLGPUType);
      pnlTop.add(RBAMD);
      pnlTop.add(RBNvidia);
      pnlTop.add(LBLMinerChoice);
      pnlTop.add(JCBMinerUsed);
      pnlTop.add(LBLMiningPools);
      pnlTop.add(JCBMiningPools);
      pnlTop.add(LBLServerLocation);
      pnlTop.add(RBAsia);
      pnlTop.add(RBEurope);
      pnlTop.add(RBUSA);
      pnlTop.add(LBLWalletAddress);
      pnlTop.add(TFWalletAddress);
      pnlTop.add(LBLNickName);
      pnlTop.add(TFMinerNickName);
      pnlTop.add(BTNGenerateBatchFile);

   }

    public void buildBottomPanel()
   {
      pnlBottom = new JPanel();
      image = new ImageIcon(getClass().getResource("ColorLogo2.png"));
      lblimage = new JLabel(image);
      LBLDonate = new JLabel("                                    If You Found This Program Useful, Please Consider Donating:                                    ");
      BTNDonate = new JButton("Donate");
      BTNDonate.addActionListener(new DonateButtonListener());
      
      pnlBottom.add(lblimage);
      pnlBottom.add(LBLDonate);
      pnlBottom.add(BTNDonate);
      

   }
   private class DonateButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         ImageIcon Donate;
         JLabel donate;
         Donate = new ImageIcon(getClass().getResource("Donate.png"));
         donate = new JLabel(Donate);
         
         JOptionPane.showMessageDialog(null, donate);
      }
      
   }
   
   private class CalcButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String WalletAddress = TFWalletAddress.getText();
         String WalletNickname = TFMinerNickName.getText();
         Object Mined = JCBMining.getSelectedItem();
         Object MinerProgramUsed = JCBMinerUsed.getSelectedItem();
         Object MinedPool = JCBMiningPools.getSelectedItem();
         String serverAndMined = "";
         String minerProgram = "";
         String BatchFile = "";
         String port = "";
         
         if(WalletNickname.length() == 0)
         {
            WalletNickname = "NoName";
         }
         if(Mined == "Ravencoin (RVN)" && (MinerProgramUsed == "Claymore Miner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin is not supported by the mining program you have selected.");
         }
         if(Mined == "Ravencoin (RVN)" && (MinerProgramUsed == "ETHMiner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin is not supported by the mining program you have selected.");
         }
         if(Mined == "Ravencoin (RVN)" && (MinerProgramUsed == "Phoenix Miner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin is not supported by the mining program you have selected.");
         }
         if(Mined == "Ravencoin (RVN)" && (MinerProgramUsed == "EWBF's ZCash Miner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin is not supported by the mining program you have selected.");
         }
         if(Mined == "Ravencoin (RVN)" && MinedPool != "Crypto Pool Party")
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin is not supported by the mining pool you have selected.");
         }
         if(Mined == "Ravencoin (RVN)" && RBAMD.isSelected() && MinerProgramUsed == "CCMiner")
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin cannot be mined on an AMD GPU using CCMiner\n Please use SGMiner to mine Ravencoin on an AMD GPU");
         }
         if(Mined == "Ravencoin (RVN)" && RBNvidia.isSelected() && MinerProgramUsed == "SGMiner")
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ravencoin cannot be mined on a Nvidia GPU using SGMiner\n Please use CCMiner to mine Ravencoin on a Nvidia GPU");
         }

         if((Mined == "Ethereum (ETH)" || Mined == "Ethereum Classic (ETC)") && (MinerProgramUsed == "EWBF's ZCash Miner" || MinerProgramUsed == "CCMiner" || MinerProgramUsed == "SGMiner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ethereum & Ethereum Classic are not supported by the mining program you have selected.");
         }
         if(Mined == "Callisto (CLO)" && MinerProgramUsed != "Claymore Miner")
         {
            JOptionPane.showMessageDialog(null, "ERROR!: This program only supports generating .bat files used for mining Callisto on Claymore Miner");
         }

         if((Mined == "ZCash (ZEC)" || Mined == "ZClassic (ZCL)") && (MinerProgramUsed != "EWBF's ZCash Miner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: ZCash & ZClassic are not supported by the mining program you have selected.");
         }
         if((Mined == "Zencash (ZEN)") && (MinerProgramUsed != "EWBF's ZCash Miner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Zencash is not supported by the mining program you have selected.");
         }
         if((Mined == "Bitcoin Private (BTCP)") && (MinerProgramUsed != "EWBF's ZCash Miner"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Bitcoin Private is not supported by the mining program you have selected.");
         }
         if((Mined == "ZClassic (ZCL)") && (MinedPool == "Ethermine" || MinedPool == "Crypto Pool Party"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: ZClassic is not available on the mining pool you have selected.");
         }
         if(Mined == "ZCash (ZEC)" && MinedPool == "Crypto Pool Party")
         {
            JOptionPane.showMessageDialog(null, "ERROR!: ZCash is not available on the mining pool you have selected.");
         }
         if(Mined == "Zencash (ZEN)" && (MinedPool == "Ethermine" || MinedPool == "Crypto Pool Party"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Zencash is not available on the mining pool you have selected.");
         }
         if(Mined == "Bitcoin Private (BTCP)" && (MinedPool == "Ethermine" || MinedPool == "Crypto Pool Party"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Bitcoin Private is not available on the mining pool you have selected.");
         }
         if(Mined == "Callisto (CLO)" && (MinedPool == "Ethermine" || MinedPool == "Crypto Pool Party"))
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Callisto is not available on the mining pool you have selected.");
         }
         if((Mined == "Ethereum (ETH)" || Mined == "Ethereum Classic (ETC)") && MinedPool == "Crypto Pool Party")
         {
            JOptionPane.showMessageDialog(null, "ERROR!: Ethereum and Ethereum Classic are not available on the mining pool you have selected.");
         }

         if(Mined == "Ethereum (ETH)" && RBAsia.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "asia1.ethermine.org:4444";
         }
         else if(Mined == "Ethereum (ETH)" && RBEurope.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "eu1.ethermine.org:4444";         
         }
         else if(Mined == "Ethereum (ETH)" && RBUSA.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "us1.ethermine.org:4444";                  
         }
         else if(Mined == "Ethereum Classic (ETC)" && RBAsia.isSelected() && MinedPool == "Ethermine")
         {
            JOptionPane.showMessageDialog(null, "There is no Asia server available for Mining Ethereum Classic on Ethermine, your server has been set to USA.");
            serverAndMined = "us1-etc.ethermine.org:4444";
         }
         else if(Mined == "Ethereum Classic (ETC)" && RBEurope.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "eu1-etc.ethermine.org:4444";         
         }
         else if(Mined == "Ethereum Classic (ETC)" && RBUSA.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "us1-etc.ethermine.org:4444";                  
         }
         else if(Mined == "Ethereum (ETH)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "asia-eth.2miners.com:2020";
         }
         else if(Mined == "Ethereum (ETH)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "eth.2miners.com:2020";         
         }
         else if(Mined == "Ethereum (ETH)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "us-eth.2miners.com:2020";                  
         }
         else if(Mined == "Ethereum Classic (ETC)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "asia-etc.2miners.com:1010";
         }
         else if(Mined == "Ethereum Classic (ETC)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "etc.2miners.com:1010";         
         }
         else if(Mined == "Ethereum Classic (ETC)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "us-etc.2miners.com:1010";                  
         }
         else if(Mined == "ZCash (ZEC)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "asia-zec.2miners.com";
	         port = "1010";
         }
         else if(Mined == "ZCash (ZEC)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "zec.2miners.com";  
	         port = "1010";       
         }
         else if(Mined == "ZCash (ZEC)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "us-zec.2miners.com";  
            port = "1010";                
         }
         else if(Mined == "ZCash (ZEC)" && RBAsia.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "asia1-zcash.flypool.org";
            port = "3333";
         }
         else if(Mined == "ZCash (ZEC)" && RBEurope.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "eu1-zcash.flypool.org";
            port = "3333";        
         }
         else if(Mined == "ZCash (ZEC)" && RBUSA.isSelected() && MinedPool == "Ethermine")
         {
            serverAndMined = "us1-zcash.flypool.org";
            port = "3333";                 
         }
         else if(Mined == "ZClassic (ZCL)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "zcl.2miners.com";
	         port = "2020";
            JOptionPane.showMessageDialog(null, "ZClassic does not have an Asia mining server, your server has been set to Europe");
         }
         else if(Mined == "ZClassic (ZCL)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "zcl.2miners.com";  
	         port = "2020";       
         }
         else if(Mined == "ZClassic (ZCL)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "zcl.2miners.com";  
            port = "2020";
            JOptionPane.showMessageDialog(null, "ZClassic does not have an USA mining server, your server has been set to Europe");                
         }
         else if(Mined == "Bitcoin Private (BTCP)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "btcp.2miners.com";
	         port = "1010";
            JOptionPane.showMessageDialog(null, "Bitcoin Private does not have an Asia mining server, your server has been set to Europe");
         }
         else if(Mined == "Bitcoin Private (BTCP)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "btcp.2miners.com";  
	         port = "1010";       
         }
         else if(Mined == "Bitcoin Private (BTCP)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "btcp.2miners.com";  
            port = "1010";
            JOptionPane.showMessageDialog(null, "Bitcoin Private does not have an USA mining server, your server has been set to Europe");                
         }
         else if(Mined == "Zencash (ZEN)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "asia-zen.2miners.com";
	         port = "3030";
         }
         else if(Mined == "Zencash (ZEN)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "zen.2miners.com";  
	         port = "3030";       
         }
         else if(Mined == "Zencash (ZEN)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "us-zen.2miners.com";  
            port = "3030";                
         }
         else if(Mined == "Callisto (CLO)" && RBAsia.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "asia-clo.2miners.com";
	         port = "3030";
         }
         else if(Mined == "Callisto (CLO)" && RBEurope.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "clo.2miners.com";  
	         port = "3030";       
         }
         else if(Mined == "Callisto (CLO)" && RBUSA.isSelected() && MinedPool == "2Miners")
         {
            serverAndMined = "us-clo.2miners.com";  
            port = "3030";               
         }
         else if(Mined == "Ravencoin (RVN)" && MinedPool == "Crypto Pool Party")
         {
            serverAndMined = "stratum+tcp://cryptopool.party:3636";
         }
         else
         {
            serverAndMined = "";
         }
         
         if (RBUSA.isSelected() == false && RBAsia.isSelected() == false && RBEurope.isSelected() == false)
         {
            JOptionPane.showMessageDialog(null, "ERROR!: You have not selected a mining server!");
            System.exit(0);
         }
         if (RBAMD.isSelected() == false && RBNvidia.isSelected() == false)
         {
            JOptionPane.showMessageDialog(null, "ERROR!: You have not selected a GPU type!");
            System.exit(0);
         }
         if (TFWalletAddress.getText().length() == 0)
         
         {
            JOptionPane.showMessageDialog(null, "ERROR!: You have not entered your wallet address!");
            System.exit(0);
         }
         if(MinerProgramUsed == "Claymore Miner")
         {
            minerProgram = "EthDcrMiner64.exe";
         }
         else if(MinerProgramUsed == "ETHMiner")
         {
            minerProgram = "ethminer.exe";
         }
         else if(MinerProgramUsed == "Phoenix Miner")
         {
            minerProgram = "PhoenixMiner.exe";
         }
         else if(MinerProgramUsed == "EWBF's ZCash Miner")
         {
            minerProgram = "miner.exe";
         }
         else if(MinerProgramUsed == "CCMiner")
         {
            minerProgram = "ccminer-x64.exe";
         }
         else if(MinerProgramUsed == "SGMiner")
         {
            minerProgram = "sgminer.exe";
         }
         else
         {
            minerProgram = "";
         }
         if(MinerProgramUsed == "Claymore Miner" && (RBNvidia.isSelected() || RBAMD.isSelected()) && (Mined == "Ethereum Classic (ETC)" || Mined == "Ethereum (ETH)"))
         {
            BatchFile = "setx GPU_FORCE_64BIT_PTR 0\r\n" +
                         "setx GPU_MAX_HEAP_SIZE 100\r\n" +
                         "setx GPU_USE_SYNC_OBJECTS 1\r\n" +
                         "setx GPU_MAX_ALLOC_PERCENT 100\r\n" +
                         "setx GPU_SINGLE_ALLOC_PERCENT 100\r\n" +
                         minerProgram + " -epool " + serverAndMined + " -ewal " + WalletAddress + "." + WalletNickname + " -epsw x";
                         
                         try
                            {
                              if(Mined == "Ethereum Classic (ETC)")
                              {
                                 File batchfile = new File("ETC-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                              else
                              {
                                 File batchfile = new File("ETH-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                                    
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
          
         }
       
         if(MinerProgramUsed == "ETHMiner" && (RBNvidia.isSelected() || RBAMD.isSelected()) && (Mined == "Ethereum Classic (ETC)" || Mined == "Ethereum (ETH)"))
         {
            BatchFile = "setx GPU_FORCE_64BIT_PTR 0\r\n" +
                         "setx GPU_MAX_HEAP_SIZE 100\r\n" +
                         "setx GPU_USE_SYNC_OBJECTS 1\r\n" +
                         "setx GPU_MAX_ALLOC_PERCENT 100\r\n" +
                         "setx GPU_SINGLE_ALLOC_PERCENT 100\r\n" +
                         minerProgram + " -P stratum://" + WalletAddress + "." + WalletNickname + ":password@" + serverAndMined + " -G\r\n" +
                         "pause";
                         
                         try
                            {
                              if(Mined == "Ethereum Classic (ETC)")
                              {
                                 File batchfile = new File("ETC-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                              else
                              {
                                 File batchfile = new File("ETH-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                                    
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
         }
         if(MinerProgramUsed == "Phoenix Miner" && (RBNvidia.isSelected() || RBAMD.isSelected()) && (Mined == "Ethereum Classic (ETC)" || Mined == "Ethereum (ETH)"))
         {
            BatchFile = "setx GPU_FORCE_64BIT_PTR 0\r\n" +
                         "setx GPU_MAX_HEAP_SIZE 100\r\n" +
                         "setx GPU_USE_SYNC_OBJECTS 1\r\n" +
                         "setx GPU_MAX_ALLOC_PERCENT 100\r\n" +
                         "setx GPU_SINGLE_ALLOC_PERCENT 100\r\n" +
                         minerProgram + " -pool ssl://" + serverAndMined + " -wal " + WalletAddress + "." + WalletNickname + "\r\n" +
                         "pause";
                         
                         try
                            {
                              if(Mined == "Ethereum Classic (ETC)")
                              {
                                 File batchfile = new File("ETC-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                              else
                              {
                                 File batchfile = new File("ETH-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                                    
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
         }
         if(MinerProgramUsed == "EWBF's ZCash Miner" && ((RBNvidia.isSelected() || RBAMD.isSelected()) && Mined == "ZCash (ZEC)") || (Mined == "ZClassic (ZCL)" || Mined == "Zencash (ZEN)" || Mined == "Bitcoin Private (BTCP)" && MinedPool != "Ethermine"))
         {
            BatchFile = ":restart\r\n" +
                        "@echo  off\r\n" +
                        "TIMEOUT 5\r\n" +
                        minerProgram + " --server " + serverAndMined + " --port " + port + " --user " + WalletAddress + "." + WalletNickname + " --pass x --eexit 3 --pec --fee 0\r\n" +
                        "ping 127.0.0.1 > nul\r\n" +
                        "goto :restart";
                         
                         try
                            {
                              if(Mined == "ZCash (ZEC)")
                              {
                                 File batchfile = new File("ZEC-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                              else if(Mined == "ZClassic (ZCL)")
                              {
                                 File batchfile = new File("ZCL-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                              else if(Mined == "Bitcoin Private (BTCP)")
                              {
                                 File batchfile = new File("BTCP-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                              else
                              {
                                 File batchfile = new File("ZEN-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();
                              }
                                    
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
          
         }
         if(MinerProgramUsed == "Claymore Miner" && (RBNvidia.isSelected() || RBAMD.isSelected()) && (Mined == "Callisto (CLO)"))
         {
            BatchFile = "setx GPU_FORCE_64BIT_PTR 0\r\n" +
                         "setx GPU_MAX_HEAP_SIZE 100\r\n" +
                         "setx GPU_USE_SYNC_OBJECTS 1\r\n" +
                         "setx GPU_MAX_ALLOC_PERCENT 100\r\n" +
                         "setx GPU_SINGLE_ALLOC_PERCENT 100\r\n" +
                         minerProgram + " -epool " + serverAndMined + ":" + port + " -allcoins etc -allpools 0 -eworker " + WalletNickname + " -ewal " + WalletAddress + " -epsw x";
                         
                         try
                            {
                                 File batchfile = new File("CLO-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();                          
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
          
         }
         if(MinerProgramUsed == "CCMiner" && RBNvidia.isSelected() && (Mined == "Ravencoin (RVN)"))
         {
            BatchFile = minerProgram + " -a x16r -o " + serverAndMined + " -u " + WalletAddress;
                         
                         try
                            {                     
                                 File batchfile = new File("RVN-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();                                                           
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
         }
         if(MinerProgramUsed == "SGMiner" && RBAMD.isSelected() && (Mined == "Ravencoin (RVN)"))
         {
            BatchFile = "setx GPU_FORCE_64BIT_PTR 0\r\n" +
                         "setx GPU_MAX_HEAP_SIZE 100\r\n" +
                         "setx GPU_USE_SYNC_OBJECTS 1\r\n" +
                         "setx GPU_MAX_ALLOC_PERCENT 100\r\n" +
                         "setx GPU_SINGLE_ALLOC_PERCENT 100\r\n" +
                         minerProgram + " -k x16r -o " + serverAndMined + " -u " + WalletAddress + " -p c=RVN -I 20";
                         
                         try
                            {                     
                                 File batchfile = new File("RVN-Miner.bat");
                                 FileWriter fr = new FileWriter(batchfile, false);
                                 fr.write(BatchFile);
                                 fr.close();                                                           
                            }
                         catch(Exception a)
                            {
                            }
                        
                         JOptionPane.showMessageDialog(null, "Your batch file has been generated!\n Your batch file was saved to the folder that contains this program\n(Most likely the downloads folder).");
                         System.exit(0);
         }


      }
    }
 
   public static void main(String[] args)
   {
      new MinerBatchFileGenerator(); 
   }
}


