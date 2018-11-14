package br.edu.infnet.gorjeta;

public class Botao {


    public interface OnClickListener{
        public void onClick(Botao btn);
    }

    private String text;
    private int width;
    private int height;
    private OnClickListener myListener;

    public Botao(String text, int width, int height) {
        this.text = text;
        this.width = width;
        this.height = height;
    }

    public void clicked(){
        myListener.onClick(this);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
