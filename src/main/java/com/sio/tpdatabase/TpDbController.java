package com.sio.tpdatabase;

import com.sio.tpdatabase.models.Product;
import com.sio.tpdatabase.services.ProductService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TpDbController implements Initializable {
    private final ProductService productService;

    @FXML
    private TableColumn<Product,String> tcName;
    @FXML
    private TableColumn<Product,Integer> tcPrice;
    @FXML
    private TableColumn<Product,Integer> tcId;
    @FXML
    private TableColumn<Product,String> tcDescription;
    @FXML
    private TableView<Product> tvProducts;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtPriceFilter;

    public TpDbController(ProductService productService) {
        this.productService = productService;
    }

    @FXML
    protected void onAddButtonClick() {
        String name = txtName.getText();
        String description = txtDescription.getText();
        int price  = Integer.parseInt(txtPrice.getText());
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        productService.save(product);
        tvProducts.setItems(FXCollections.observableArrayList(productService.findAll()));

    }

    @FXML
    protected void onTableViewSelectedLine() {
        Product product = tvProducts.getSelectionModel().getSelectedItem();
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        txtPrice.setText(String.valueOf(product.getPrice()));
    }

    @FXML
    protected void onSearchTermsChange() {
        tvProducts.setItems(FXCollections.observableArrayList(productService.findByName(txtSearch.getText())));
    }

    @FXML
    protected void onPriceFilterChange() {

        int price = Integer.parseInt(txtPriceFilter.getText());
        System.out.println(price);

        tvProducts.setItems(FXCollections.observableArrayList(
                productService.findByPriceGreaterThanEqual(price)
        ));
    }



    @FXML
    protected void onUpdateButtonClick() {
        Product product = tvProducts.getSelectionModel().getSelectedItem();
        String name = txtName.getText();
        String description = txtDescription.getText();
        int price = Integer.parseInt(txtPrice.getText());

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        productService.save(product);
        tvProducts.setItems(FXCollections.observableArrayList(productService.findAll()));

    }

    @FXML
    protected void onDeleteButtonClick() {
        Product product = tvProducts.getSelectionModel().getSelectedItem();
        productService.delete(product);
        txtName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        tvProducts.setItems(FXCollections.observableArrayList(productService.findAll()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));

        tvProducts.setItems(FXCollections.observableArrayList(productService.findAll()));

    }
}
