import java.io.InputStreamReader
import java.io.BufferedReader

object BFInterpreter {
	var bytes : Array[Int] = null;
	var index : Int = 0;

	def main(args : Array[String]) : Unit = {

		while (true) {
			var code : String = null;

			bytes = new Array[Int](30000);
			if (args.length > 0) {
				code = args(0);
			}
			else {
				System.out.println();
				System.out.println("Enter your Brainfuck code");
				var reader : BufferedReader = new BufferedReader(new InputStreamReader(System.in));
				code = reader.readLine();
			}

			this.eval(code);
		}
	}

	def eval(code : String) : Unit = {
		var i : Int = 0;
		var c : Char = 0;
		while (i < code.length()) {
			c = code.charAt(i);

			if (c == '>') {
				index += 1;
			}
			else if (c == '<') {
				index -= 1;
			}
			else if (c == '+') {
				bytes(index) += 1;
			}
			else if (c == '-') {
				bytes(index) -= 1;
			}
			else if (c == ',') {
				bytes(index) = System.in.read();
			}
			else if (c == '.') {
				System.out.print(bytes(index).asInstanceOf[Char]);
			}
			else if (c == '[') {
				if (bytes(index) == 0) {
					i = code.indexOf(']');
				}
			}
			else if (c == ']') {
				if (bytes(index) != 0) {
					i = code.lastIndexOf('[');
				}
			}

			i += 1;
		}
	}
}
