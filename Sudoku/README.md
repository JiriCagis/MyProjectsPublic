<h1> Sudoku 1.0 </H1>
Sudoku is a logical numbers game for one player. The classic Sudoku game involves a grid of 81 squares. The grid is divided into nide block, each block contain nine squeares. The rule of the game are simple: Each of the nine block should contain all the numbers 1-9 within its squares. Each number can only once appear in a row, a column and a block. The difficulty lies in that all a vertical nine-square column, a horizontal nine-square line and a box must also contain the numbembers 1-9 without repettion or omission.

<H3> Description algorithm generate sudoku</H3>
The algorithm inicialize sequence numbers for filling fields. After it recursive tries put one number from the sequence numbers and it test if the number really belong to this field. The algorithm tries put another numbers consecutive. It returns back to previous field and examine other number from sequence if it does not put nothing a number from the sequence. In last step remove numbers from a grid on random positions. Difficult the game is given how many fields will be removed from the grid after generate.

<H3>Main functions</H3>
<ul>
	<li>Sudoku is dynamic generate</li>
	<li>Support different levels (beginner, classical and master)</li>
	<li>Verify correct after complete all fields in the game</li>
	<li>Save game after close application and return game when you open application.</li>
	<li>Window is dynamic stretchable.</li>
	<li>Application remember your position and size a window</li>
</ul>

<H3>Technologie</H3>
- Programming language; Java 7
- Graphical interface: Swing
- Back-tracking: Generate numbers grid
- Persist data: XML format
- Unit testing: JUnit
