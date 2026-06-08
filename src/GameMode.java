public enum GameMode {
    HUMAN_VS_HUMAN {
        @Override
        public String toString() {
            return "Human vs Human";
        }
    },
    HUMAN_VS_BOT {
        @Override
        public String toString() {
            return "Human vs Bot";
        }
    }
}
