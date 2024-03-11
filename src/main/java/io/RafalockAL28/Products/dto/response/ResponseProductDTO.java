    package io.RafalockAL28.Products.dto.response;

    import io.RafalockAL28.Products.entity.Product;
    import lombok.Getter;

    @Getter
    public class ResponseProductDTO {

        private Long id;
        private String name;
        private  String type;
        private Double price;
        private  int qtde;

        public ResponseProductDTO(Product product) {
            this.id = product.getId();
            this.name = product.getName();
            this.type = product.getType();
            this.price = product.getPrice();
            this.qtde = product.getQtde();
        }
    }
