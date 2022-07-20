package Day44.exam01_nestedinterface;

public class Button {
    OnClickListener listener;
    void setOnClickListener(OnclickListener listener) { this.listener = listener;}

    void touch(){
        listener.onClick();
    }

    interface OnClickListener{
        void onClick();
    }

}
