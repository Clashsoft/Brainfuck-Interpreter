import java.io.InputStreamReader
import java.io.BufferedReader
import scala.io.StdIn

object BFInterpreter {

	def main(args : Array[String]) : Unit = {

		while (true) {
			try {
				if (args.length > 0) {
					eval(args(0));
				}
				else {
					System.out.println("Enter your Brainfuck code");
					eval(StdIn.readLine());
				}
			}
			catch {
				case t : Throwable => t.printStackTrace()
			}
		}
	}

	def eval(code : String) : Unit = {
		var i : Int = 0;
		var data : Array[Int] = new Array[Int](30000)
		var index : Int = 0;

		while (i < code.length()) {
			var c : Char = code.charAt(i);

			c match {
				case '>' => index += 1;
				case '<' => index -= 1;
				case '+' => data(index) += 1;
				case '-' => data(index) -= 1;
				case '.' => println(data(index).asInstanceOf[Char]);
				case ',' => data(index) = StdIn.readChar();
				case '[' => if (data(index) == 0) {
					i = code.indexOf(']');
				}
				case ']' => if (data(index) != 0) {
					i = code.lastIndexOf('[');
				}
			}

			i += 1;
		}
	}
}
