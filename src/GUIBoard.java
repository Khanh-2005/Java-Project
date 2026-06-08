import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.Set;


// https://www.youtube.com/watch?v=SNYFjgz4bU4
// png files from https://marcelk.net/chess/pieces/

/*
1) Class Constructor
2) Board Labelling Methods
3) Board Formatting Methods
4) Information Panel Method
5) Piece Movement Methods
6) Game Saving Class
7) Piece Movement Methods
8) Overridden Methods
*/

/**
 * The GUIBoard class contains all the methods and classes used to display the game of chess
 */

public class GUIBoard extends JFrame {

    // the pieces used for the game
    private final Pieces pieces;
    private final ChessGameController controller;
    private final GameMode gameMode;
    private final int dimension = BOARD.LAST_RANK.getRankVal();
    private final int firstRank = BOARD.FIRST_RANK.getRankVal();
    private final char firstFile = BOARD.FIRST_FILE.getFileVal();
    private final char lastFile = BOARD.LAST_FILE.getFileVal();
    private final char charFile = (char) (firstFile - 1);
    // text pane containing the moves of a game
    private final JTextPane movePane = new JTextPane();
    // text pane containing the message at the end of the game (mate or draw)
    private final JTextPane matePane = new JTextPane();
    private final JLabel statusLabel = new JLabel("White to move.");
    private final JLabel modeLabel = new JLabel();

    // the board on which the game is played
    private final JButton[][] board = new JButton[dimension][dimension];
    // the button used to save a game
    private final JButton saveButton = new JButton("Save Game");
    private final JButton newGameButton = new JButton("New Game");
    private final JComboBox<GameMode> modeSelector = new JComboBox<>(GameMode.values());

    // colours for the board
    private final Color brown = new Color(118, 78, 52);
    private final Color pastel = new Color(238, 217, 181);
    private final Color intermediate = new Color(246, 205, 91);
    private final Color panelAccent = new Color(222, 156, 64);
    public static final Color infoColour = new Color(35, 39, 44);

    // size of a square in board
    private static final int tileSize = 88;

    // icon for squares without pieces
    private final BufferedImage invisible = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
    private final ImageIcon invisibleIcon = new ImageIcon(invisible);

    // build up the game save file
    private static final StringBuilder str = new StringBuilder();

    // ActionHandler used in GUIBoard construcotr
    ButtonHandle gameClick = new ButtonHandle();

    //________________________________________________Class Constructor________________________________________________

    /**
     * The GUIBoard construcotr creates the chess board
     * It assigns buttons to the boards 2D array, and adds their ActionListeners (gameClick)
     * Adds all the additional JPanels (i.e to show files, rows, save panels, etc...)
     * Sets up the JFrame
     * @param p the pieces HashMap used for the game
     */

    public GUIBoard(Pieces p) {
        this(p, GameMode.HUMAN_VS_HUMAN);
    }

    public GUIBoard(Pieces p, GameMode mode) {
        setTitle("CHESS23");
        setBackground(Color.black);
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());

        pieces = p;
        gameMode = mode;
        controller = new ChessGameController(pieces, gameMode);
        str.setLength(0);

        JPanel boardPanel = new JPanel(new GridLayout(dimension, dimension));
        for (int rank = dimension; rank >= firstRank; rank--) {
            for (int file = 1; file <= dimension; file++) {
                char fileChar = (char) (charFile + file);
                Coordinate tileCoord = new Coordinate(fileChar, rank);
                board[rank - 1][file - 1] = setButton(tileCoord, p);
                board[rank - 1][file - 1].addActionListener(gameClick);
                boardPanel.add(board[rank - 1][file - 1]);
            }
        }

        JPanel fullBoardPanel = new JPanel(new BorderLayout());
        fullBoardPanel.add(createFileLabelsTop(),BorderLayout.NORTH);
        fullBoardPanel.add(createRankLabelsLeft(),BorderLayout.WEST);
        fullBoardPanel.add(boardPanel, BorderLayout.CENTER);
        fullBoardPanel.add(createRankLabelsRight(),BorderLayout.EAST);
        fullBoardPanel.add(createFileLabelsBottom(), BorderLayout.SOUTH);
        contents.add(fullBoardPanel, BorderLayout.WEST);
        contents.add(createInfoPanel(), BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //________________________________________________Board Labelling Methods________________________________________________

    /**
     * Shows the ranks (1-8) of the board
     * @return a JPanel with JLabels containing the ranks of the chess board
     */

    public JPanel createRanks() {
        JPanel ranks = new JPanel(new GridLayout(dimension,0));
        ranks.setBackground(pastel);

        int rankPad = 4;

        for (int rank = dimension; rank >= firstRank; rank--) {
            JLabel rankLabel = new JLabel(String.valueOf(rank));
            rankLabel.setFont(new Font("TimesRoman", Font.BOLD, 23));
            rankLabel.setForeground(brown);
            rankLabel.setBorder(new EmptyBorder(0,rankPad,0,rankPad));
            ranks.add(rankLabel);
        }

        return ranks;
    }

    /**
     * Shows the files (a - h) of the board
     * @return a JPanel with JLabels containing the files of the chess board
     */

    public JPanel createFiles() {
        JPanel files = new JPanel(new GridLayout(0,dimension));
        files.setBackground(pastel);

        int filePad = 42;

        for (char file = firstFile; file <= lastFile; file++) {
            JLabel fileLabel = new JLabel(String.valueOf(file));

            fileLabel.setFont(new Font("TimesRoman", Font.BOLD, 23));
            fileLabel.setForeground(brown);
            fileLabel.setBorder(new EmptyBorder(0,filePad,0,filePad));
            files.add(fileLabel);
        }
        return files;
    }

    /**
     * Shows a brown border for the board
     * @return a JPanel that acts as a border for the baord
     */

    public JPanel createBorder() {
        JPanel border = new JPanel();
        border.setBackground(brown);

        return border;
    }

    /**
     * @return a JPanel, acting as a corner for the board
     */

    public Border createCorner() {
        int borderPad = 20;
        return new MatteBorder(0,borderPad,0,borderPad,pastel);
    }

    /**
     * Displays the ranks to the left of the board
     * @return a JPanel displaying the ranks and the border displayed to the left of the board
     */

    public JPanel createRankLabelsLeft() {
        JPanel rankLabels = new JPanel(new BorderLayout());

        rankLabels.add(createRanks(), BorderLayout.WEST);
        rankLabels.add(createBorder(), BorderLayout.EAST);

        return rankLabels;
    }

    /**
     * Displays the files to the bottom of the board
     * @return a JPanel displaying the files and the border displayed to the bottom of the board
     */

    public JPanel createFileLabelsBottom() {
        JPanel fileLabels = new JPanel(new BorderLayout());

        fileLabels.add(createFiles(), BorderLayout.SOUTH);
        fileLabels.add(createBorder(), BorderLayout.NORTH);

        fileLabels.setBorder(createCorner());

        return fileLabels;
    }

    /**
     * Displays the ranks to the right of the board
     * @return a JPanel displaying the ranks and the border displayed to the right of the board
     */

    public JPanel createRankLabelsRight() {
        JPanel rankLabels = new JPanel(new BorderLayout());

        rankLabels.add(createRanks(), BorderLayout.EAST);
        rankLabels.add(createBorder(), BorderLayout.WEST);

        return rankLabels;
    }

    /**
     * Displays the files to the top of the board
     * @return a JPanel displaying the files and the border displayed to the top of the board
     */

    public JPanel createFileLabelsTop() {
        JPanel fileLabels = new JPanel(new BorderLayout());

        fileLabels.add(createFiles(), BorderLayout.NORTH);
        fileLabels.add(createBorder(), BorderLayout.SOUTH);

        fileLabels.setBorder(createCorner());

        return fileLabels;
    }

    //________________________________________________Board Formatting Methods________________________________________________

    /**
     * Sets the background of a button, based on whether its a white or black square
     * @param coordinate the coordinate of the square being considered
     * @param b the button at the given square
     */

    private void backgroundSetter (Coordinate coordinate, JButton b){
        int signature = coordinate.getFile() - charFile + coordinate.getRank();
        if (signature % 2 == 0) {
            b.setBackground(brown);
        } else {
            b.setBackground(pastel);
        }
    }

    /**
     * Formats a JButton to be used in the board 2D array
     * @param coordinate the coordinate of the square being considered
     * @param pieces the pieces being used for the game
     * @return a JButton, correctly formatted with the piece it represents
     */

    private JButton setButton (Coordinate coordinate, Pieces pieces){
        JButton b = new JButton();

        Piece piece;

        backgroundSetter(coordinate, b);

        if (pieces.getPieces().get(coordinate) == null) {
            piece = null;
        } else {
            piece = pieces.getPiece(coordinate);
        }

        if (piece != null)
            b.setIcon(piece.getImageIcon());
        else
            b.setIcon(invisibleIcon);

        formatButton(b);

        return b;
    }

    /**
     * Correctly formats the properties of the JButton used in the boards array
     * @param b the JButton being formatted
     */

    private void formatButton (JButton b) {
        b.setSize(tileSize, tileSize);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setVisible(true);
    }

    /**
     * Correctly formats the properties of an invisible JButton used in the boards array
     * @param b the JButton being formatted
     */

    public static void formatInvisibleButton (JButton b) {
        b.setSize(tileSize, tileSize);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setVisible(true);
    }

    /**
     * Removes the ActionListener of all the JButtons of the board
     * This is done once a game is finished
     */

    private void disableBoardButtons() {
        for (int row = 0; row < dimension; row++) {
            for (int file = 0; file < dimension; file++) {
                board[row][file].removeActionListener(gameClick);
            }
        }
    }

    //________________________________________________Information Panel Method________________________________________________

    /**
     * The "Info Panel" is a JPanel containing information of the game (the moves being played)
     * and whether its check mate or a draw.
     * It contains the saveButton, used to save a game at any given time
     * @return a JPanel containing information on the game
     */

    private JPanel createInfoPanel() {

        JPanel movePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        movePanel.setBackground(infoColour);
        movePanel.setVisible(true);
        movePanel.setPreferredSize(new Dimension(300,800));
        movePanel.setBorder(new EmptyBorder(24, 20, 24, 20));

        JLabel titleLabel = new JLabel("CHESS23");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        statusLabel.setForeground(panelAccent);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(new EmptyBorder(8, 0, 4, 0));

        modeLabel.setText(gameMode.toString());
        modeLabel.setForeground(new Color(198, 205, 213));
        modeLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        modeLabel.setBorder(new EmptyBorder(0, 0, 14, 0));

        modeSelector.setSelectedItem(gameMode);
        modeSelector.setBackground(new Color(52, 58, 64));
        modeSelector.setForeground(Color.white);
        modeSelector.setFocusable(false);
        modeSelector.addActionListener(actionEvent -> {
            GameMode selectedMode = (GameMode) modeSelector.getSelectedItem();
            if (selectedMode != null && selectedMode != gameMode)
                startNewGame(selectedMode);
        });

        movePane.setEditable(false);
        movePane.setForeground(Color.white);
        movePane.setBackground(infoColour);
        movePane.setFont(new Font("Arial", Font.BOLD, 14));
        movePane.setBorder(new EmptyBorder(14,14,14,14));
        JScrollPane scrollMoves = new JScrollPane(movePane);
        scrollMoves.setBorder(BorderFactory.createLineBorder(new Color(70, 76, 84)));

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        movePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        movePanel.add(statusLabel, gbc);

        gbc.gridy = 2;
        movePanel.add(modeLabel, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        movePanel.add(scrollMoves,gbc);

        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(14, 0, 0, 0);
        movePanel.add(modeSelector, gbc);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        actionPanel.setBackground(infoColour);
        styleActionButton(newGameButton, new Color(80, 91, 102));
        styleActionButton(saveButton, panelAccent);
        newGameButton.addActionListener(new NewGameHandle());
        saveButton.addActionListener(new SaveHandle());

        actionPanel.add(newGameButton);
        actionPanel.add(saveButton);

        gbc.gridy = 5;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 0, 16, 0);
        movePanel.add(actionPanel,gbc);

        matePane.setEditable(false);
        matePane.setForeground(Color.white);
        matePane.setBackground(infoColour);
        matePane.setFont(new Font("Arial", Font.BOLD, 16));
        matePane.setBorder(new EmptyBorder(0,0,0,0));

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        movePanel.add(matePane,gbc);

        return movePanel;
    }

    private void styleActionButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 42));
    }

    //________________________________________________Piece Movement Methods________________________________________________

    /**
     * Changes the turn of the game
     */

    /**
     * This is used to illuminate the potential moves of the piece that is being clicked on
     * @param potentials the potential moves of the piece being clicked on
     */
    private void processClick(Set<Coordinate> potentials) {

        for (int rank = 1; rank <= dimension; rank++) {
            for (char file = firstFile; file <= lastFile; file++) {
                int processedRank = rank - firstRank;
                int processedFile = file - firstFile;
                Coordinate potentialCoord = new Coordinate(file, rank);
                if (potentials.contains(potentialCoord))
                    board[processedRank][processedFile].setBackground(intermediate);
                else
                    backgroundSetter(potentialCoord, board[processedRank][processedFile]);
            }
        }
    }

    private void refreshBoard() {
        for (int rank = 1; rank <= dimension; rank++) {
            for (char file = firstFile; file <= lastFile; file++) {
                int processedRank = rank - firstRank;
                int processedFile = file - firstFile;
                Coordinate potentialCoord = new Coordinate(file, rank);
                backgroundSetter(potentialCoord, board[processedRank][processedFile]);
                board[processedRank][processedFile].setIcon(invisibleIcon);
                if (pieces.getPieces().get(potentialCoord) != null) {
                    Piece updatePiece = pieces.getPiece(potentialCoord);
                    board[processedRank][processedFile].setIcon(updatePiece.getImageIcon());
                }
            }
        }
    }

    /**
     * This class is used to handle the game logic for the GUI.
     */

    private class ButtonHandle implements ActionListener {

        /**
         * Used to turn 2D array "coordinates" into chess board coordinates
         * @param row the row of the 2D array
         * @param column the column of the 2D array
         * @return the Coordinate corresponding to an element of the board array with the given row and column
         */

        private Coordinate toCoordinate(int row, int column) {
            int rank = row + firstRank;
            char file = (char) (column + firstFile);

            return new Coordinate(file, rank);
        }

        /**
         * If the counter is at 0, and a button is clicked, we increase the counter and execute game click.
         * We also save the piece that has been clicked on, and assign it to movingPiece
         * If the counter is not at 0, we check the coordinate that has been selected.
         * If its a valid move for the movingPiece, then it executes makeMove on the pieces.
         * It also appends the move to str, changes the turn, reprints the board with the new pieces and clears the counter.
         * If it is an invalid move, then we consider the new clicked piece as the moving piece, and again, execute gameClick
         * If its mate, stalemate or draw, all the board buttons are disabled and the game ends.
         */

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Coordinate coordinate = null;

            JButton source = (JButton) actionEvent.getSource();

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (board[i][j].equals(source)) {
                        coordinate = toCoordinate(i, j);
                    }
                }
            }

            if (coordinate != null && Coordinate.inBoard(coordinate)) {
                MoveOutcome outcome = controller.click(coordinate);

                if (outcome.getType() == MoveOutcome.Type.SELECTED) {
                    processClick(outcome.getHighlightedMoves());
                } else {
                    refreshBoard();
                }

                str.setLength(0);
                str.append(outcome.getMoveHistory());
                movePane.setText(outcome.getMoveHistory());
                updateStatus(outcome.getGameMessage());

                if (outcome.isGameOver())
                    disableBoardButtons();
                else if (outcome.getType() == MoveOutcome.Type.MOVED)
                    playBotTurn();
            }
        }
    }

    private void playBotTurn() {
        Timer botTimer = new Timer(350, actionEvent -> {
            MoveOutcome botOutcome = controller.playBotTurnIfNeeded();
            refreshBoard();
            str.setLength(0);
            str.append(botOutcome.getMoveHistory());
            movePane.setText(botOutcome.getMoveHistory());
            updateStatus(botOutcome.getGameMessage());
            if (botOutcome.isGameOver())
                disableBoardButtons();
        });
        botTimer.setRepeats(false);
        botTimer.start();
    }

    private void updateStatus(String message) {
        String displayMessage = message;
        if (displayMessage == null || displayMessage.length() == 0)
            displayMessage = controller.getTurn().toString() + " to move.";

        if (displayMessage.endsWith("to move.")) {
            statusLabel.setText(displayMessage);
            matePane.setText("");
        } else {
            statusLabel.setText(controller.getTurn().toString() + " to move.");
            matePane.setText(displayMessage);
        }
    }

    //________________________________________________Game Saving Class________________________________________________

    /**
     * Handles the logic to save the game
     */

    private class NewGameHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            startNewGame((GameMode) modeSelector.getSelectedItem());
        }
    }

    private void startNewGame(GameMode selectedMode) {
        dispose();
        Pieces newPieces = new Pieces();
        newPieces.setGUIGame(true);
        new GUIBoard(newPieces, selectedMode);
    }

    public static class SaveHandle implements ActionListener {

        /**
         * This handles the saving of a game.
         * It produces a JOptionPane with an InputDialog.
         * We use ChessIO to validate the file path provided.
         * JOptionPane with message dialogs are then presented, based on whether the save was successful or not
         */

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            ImageIcon icon = new ImageIcon("images/WKing.png");

            UIManager.put("OptionPane.background", infoColour);
            UIManager.put("Panel.background", infoColour);
            UIManager.put("OptionPane.messageForeground", Color.white);

            String fileSave = (String) JOptionPane.showInputDialog(null,
                    "Enter a file name to save the game:",
                    "Save Game",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon,null,null);

            if (fileSave != null) {

                String filePath = ChessIO.toTxt(fileSave);

                if (ChessIO.isErrorSave(filePath)) {
                    JOptionPane.showMessageDialog(null,
                            fileSave + " is an incorrect file name.",
                            "Failed Saving",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                } else {
                    String savedGame = str.length() == 0 ? "No moves have been played yet." : str.toString();
                    if (ChessIO.saveGame(savedGame, Paths.get(filePath)))
                        JOptionPane.showMessageDialog(null,
                                "Game saved successfully on path " + filePath,
                                "Save Successful",
                                 JOptionPane.INFORMATION_MESSAGE,
                                 icon);
                    else
                        JOptionPane.showMessageDialog(null,
                                "There was an error saving the file on the path " + filePath + ". The name provided might be a duplicate.",
                                "Failed Saving",
                                JOptionPane.ERROR_MESSAGE,
                                icon);

                }
            }
        }
    }


    public static void main (String[]args){
        Pieces pieces = new Pieces();
        pieces.setGUIGame(true);
        new GUIBoard(pieces);
    }

}
