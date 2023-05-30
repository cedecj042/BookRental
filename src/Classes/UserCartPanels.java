/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import User.Cart;
import User.CartPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Aesthetics
 */
public class UserCartPanels extends UserCart{
    
    private UserCart usercart;
    UserCartDB ucdb = new UserCartDB();
    private CartPanel cartpanel;
    Cart cart;
    public UserCart getUsercart() {
        return usercart;
    }

    public void setUsercart(UserCart usercart) {
        this.usercart = usercart;
    }

    public CartPanel getCartpanel() {
        return cartpanel;
    }

    public void setCartpanel(CartPanel cartpanel) {
        this.cartpanel = cartpanel;
    }
    public void setCart(Cart cart){
        this.cart = cart;
    }
       
    
    public UserCartPanels(Book book,int quantity,int rentedDays, double amount){
        super(book,quantity,rentedDays,amount);
        this.cartpanel = new CartPanel(UserCartPanels.this);
        this.cartpanel.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checkbox = cartpanel.isCheck();
                if(checkbox){
                    UserCartPanels.this.cart.addSelected(UserCartPanels.this);
                }else {
                    System.out.println("Remove");
                    UserCartPanels.this.cart.removeSelected(UserCartPanels.this);
                }
                UserCartPanels.this.cart.execute();
            }
        });
        this.cartpanel.setRem(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(cartpanel.isCheck()){
                    UserCartPanels.this.cart.removeSelected(UserCartPanels.this);
                }
                ucdb.removeCart(UserSession.getInstance().getUser().getUserID(),UserCartPanels.this.getBook().getBookID());
                UserCartPanels.this.cart.execute();
            }
        });
        this.cartpanel.setAdd(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                UserCartPanels.this.setRentedDays(ucdb.addRentedDays(UserSession.getInstance().getUser().getUserID(),UserCartPanels.this.getBook().getBookID()));
                UserCartPanels.this.setAmount(UserCartPanels.this.getRentedDays() * UserCartPanels.this.getBook().getPrice());
                UserCartPanels.this.cart.updateSelected(UserCartPanels.this);
                UserCartPanels.this.cart.execute();
            }
        });
        this.cartpanel.setSub(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                UserCartPanels.this.setRentedDays(ucdb.minusRentedDays(UserSession.getInstance().getUser().getUserID() ,UserCartPanels.this.getBook().getBookID()));
                UserCartPanels.this.setAmount(UserCartPanels.this.getRentedDays() * UserCartPanels.this.getBook().getPrice());
                UserCartPanels.this.cart.updateSelected(UserCartPanels.this);
                UserCartPanels.this.cart.execute();
            }
        });
    }
}
