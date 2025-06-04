package kulki;

public class GamePanelTest {
    public static void main(String[] args) throws Exception {
        GamePanel panel = new GamePanel();
        panel.setSize(600, 400);
        panel.stopTimer();

        java.lang.reflect.Field xField = GamePanel.class.getDeclaredField("x");
        xField.setAccessible(true);
        java.lang.reflect.Field yField = GamePanel.class.getDeclaredField("y");
        yField.setAccessible(true);
        java.lang.reflect.Field dxField = GamePanel.class.getDeclaredField("dx");
        dxField.setAccessible(true);
        java.lang.reflect.Field dyField = GamePanel.class.getDeclaredField("dy");
        dyField.setAccessible(true);
        java.lang.reflect.Field paddleXField = GamePanel.class.getDeclaredField("paddleX");
        paddleXField.setAccessible(true);

        int paddleX = paddleXField.getInt(panel);
        xField.setInt(panel, paddleX + 20);
        yField.setInt(panel, panel.getHeight() - 20 - 10); // BALL_SIZE and PADDLE_HEIGHT
        dxField.setDouble(panel, 0);
        dyField.setDouble(panel, 1);

        int before = panel.getScore();
        panel.actionPerformed(null);
        int after = panel.getScore();
        if (after != before + 1) {
            throw new AssertionError("Score did not increase after paddle hit");
        }
    }
}
