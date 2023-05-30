/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import CustomizedClasses.RadioButtonCustom;
import User.CheckoutDetails;
import User.DeliveryPanel;
import User.Executable;

/**
 *
 * @author Aesthetics
 */
public class DeliveryPanels extends DeliveryAddress {

    private DeliveryPanel delivery;
    private boolean choosen;
    CheckoutDetails cd;
    RadioButtonCustom radio;
    Executable executable;

    public Executable getExecutable() {
        return executable;
    }

    public void setExecutable(Executable executable) {
        this.executable = executable;
    }
    
    

    public DeliveryPanel getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryPanel delivery) {
        this.delivery = delivery;
    }

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean choosen) {
        this.choosen = choosen;
    }

    public CheckoutDetails getCd() {
        return cd;
    }

    public void setCd(CheckoutDetails cd) {
        this.cd = cd;
    }

    public RadioButtonCustom getRadio() {
        return radio;
    }

    public void setRadio(RadioButtonCustom radio) {
        this.radio = radio;
    }

    public DeliveryPanels(int addressID, String userID, String name, String address, String landmark, String notes) {
        super(addressID, userID, name, address, landmark, notes);
        this.delivery = new DeliveryPanel(DeliveryPanels.this);
    }

    public void addRadio(CheckoutDetails cd) {
        this.cd = cd;
        this.delivery.getRadio().setDeliverypanel(DeliveryPanels.this);
        DeliveryPanels.this.radio = DeliveryPanels.this.delivery.getRadio();
        this.cd.addAddressGroup(DeliveryPanels.this.getRadio());
    }

}
