# Chess Game (Java Swing) ♟️

A simple chess game implemented in Java using the Swing GUI toolkit. This project demonstrates a basic playable chessboard, piece movement rules, captures, and check/checkmate detection.

## 📁 Project Structure

- `src/` - Java source files (pieces, board logic, GUI, IO)
  - `Piece.java`, `Pawn.java`, `Rook.java`, `Knight.java`, `Bishop.java`, `Queen.java`, `King.java`
  - `BOARD.java`, `Boards.java`, `GUIBoard.java`, `ChessIO.java`, etc.

## 🔧 Features

- Two-player local gameplay (human vs human)
- Graphical board and piece rendering using Java Swing
- Move validation for all standard chess pieces (pawn, rook, knight, bishop, queen, king)
- Basic check and checkmate detection
- Save/load and basic I/O utilities (if implemented in the project files)

## ⚙️ Requirements

- Java 8 or newer
- An IDE (Eclipse, IntelliJ IDEA, NetBeans) or JDK command-line tools

## 🚀 Build & Run

1. From an IDE: Import the project and run the `GUIBoard` class (or the main class that starts the GUI).

2. From command line (example):

```bash
# Compile all .java files into ./out
javac -d out src/*.java

# Run the GUI (adjust class name if different)
java -cp out GUIBoard
```

## 🧭 Usage

- Click a piece to select it, then click the destination square to move.
- The game enforces legal moves and handles captures. Check/checkmate detection is basic—please report any edge-case bugs.
