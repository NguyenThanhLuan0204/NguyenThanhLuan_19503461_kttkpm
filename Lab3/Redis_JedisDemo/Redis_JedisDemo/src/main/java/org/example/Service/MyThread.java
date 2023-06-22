package org.example.Service;

class MyThread extends Thread{

    Service service = new Service();
    public void run(){
        while (true){
            service.service("a");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
