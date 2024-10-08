package Single_Cycle

import lab_4.ALU1
import chisel3 . _
import chisel3 . util . _

class TopCore_5stage extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(32.W))
  })
  val Fetch = Module(new Fetch)
  val Decode = Module(new Decode)
  val Execute = Module(new Execute)
  val Memory = Module(new memory)
  val Wb = Module(new Wb)


  //IF_ID pipeline
  val ins = RegInit(UInt(32.W))
  ins:=Fetch.io.ins
  Decode.io.pcin:=ins
  val pc = RegInit(UInt(32.W))
  pc:= Fetch.io.pcout
  Decode.io.pcin:=pc


  //ID_IE pipeline
  val pc2 = Reg(UInt(32.W))
  pc2:= Decode.io.pcout
  Execute.io.pcin:=pc2
  val imm = Reg(UInt(32.W))
  imm := Decode.io.Imm
  Execute.io.Imm := imm
  val Instype = Reg(Bool())
  Instype:= Decode.io.Instype
  Execute.io.Instype := Instype
  val MemWrite = Reg(Bool())
  MemWrite := Decode.io.MemWrite
  Execute.io.MemWritein := MemWrite
  val func = Reg(UInt(5.W))
  func := Decode.io.func
  Execute.io.funcin:= func
  val wbselect = Reg(UInt(2.W))
  wbselect:= Decode.io.wbselect
  Execute.io.wbselectin:=wbselect
  val aluselect = Reg(Bool())
  aluselect:= Decode.io.aluselect
  Execute.io.aluselect:= aluselect
  val lengthselect = Reg(UInt(2.W))
  lengthselect:= Decode.io.lengthselect
  Execute.io.lengthselectin:= lengthselect
  val btypefun = Reg(UInt(4.W))
  btypefun:= Decode.io.btypefun
  Execute.io.fun3 := btypefun
  val pcselec = Reg(Bool())
  pcselec:= Decode.io.pcselec
  Execute.io.pcselec:=pcselec
  val btype = Reg(Bool())
  btype := Decode.io.btype
  Execute.io.btype:=btype
  val jump = Reg(Bool())
  val readmem = Reg(Bool())
  readmem:=Decode.io.readmem
  Execute.io.readmemin:=readmem
  val RegWriteout = Reg(Bool())
  RegWriteout:=Decode.io.RegWriteout
  Execute.io.RegWritein:=RegWriteout
  val RD = Reg(UInt(5.W))
  RD := Decode.io.RD
  Execute.io.RDin:= RD

  val in_A = Reg(UInt(32.W))
  val in_B = Reg (UInt(32.W))
  in_A := Decode.io.Rs1out
  in_B := Decode.io.Rs2out
  Execute.io.in_A := in_A
  Execute.io.in_B := in_B


  //IE_MEM
  val memWen = Reg(Bool())
  memWen := Execute.io.MemWriteout
  Memory.io.Wen := memWen
  val addr = Reg(UInt(32.W))
  addr:= Execute.io.out
  Memory.io.addr:=addr
  val datain = Reg(UInt(32.W))
  datain := Execute.io.in_Bout
  Memory.io.datain:=datain
  val wbselect2 = Reg(UInt(2.W))
  wbselect2:= Execute.io.wbselectout
  Memory.io.wbselectin := wbselect2
  val pc3= Reg(UInt(32.W))
  pc3:= Execute.io.pcout
  Memory.io.pcin:=pc3
  val Regwrite2 = Reg(Bool())
  Regwrite2:= Execute.io.RegWriteout
  Memory.io.regwritein:=Regwrite2
  val RDin = Reg(Bool())
  RDin := Execute.io.RDout
  Memory.io.RDin:=RDin

  //MEM_WB
  val datamemout = Reg(UInt(32.W))
  datamemout:= Memory.io.dmemdataout
  Wb.io.datamemin:=datamemout

  val aludataout = Reg(UInt(32.W))
  aludataout:= Memory.io.aludataout
  Wb.io.Aludatain:=aludataout

  val pcout4 = Reg(UInt(32.W))
  pcout4:= Memory.io.pcout
  Wb.io.pcin:=pcout4

  val wbselect3 = Reg(Bool())
  wbselect3:= Memory.io.wbselectout
  Wb.io.wbselect := wbselect3

  val Rd4 = Reg(UInt(5.W))
  Rd4 := Memory.io.RDOut
  Wb.io.Rd:= Rd4

  val Regwrite3 = Reg(Bool())
  Regwrite3 := Memory.io.regwriteout
  Wb.io.Regwrite:= Regwrite3

  //Wb to Dec
  io.out := 0.U





















}