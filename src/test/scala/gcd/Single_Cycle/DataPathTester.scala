package Single_Cycle
import chisel3._
import org.scalatest._
import chiseltest._

class DataPathTester extends FreeSpec with ChiselScalatestTester {
  "DataPath Test" in {
    test(new DataPath) { c =>
      c.clock.step()
      c.io.out.expect(1.U)

    }
  }
}