/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javitronics.javitronics.service;
import java.time.ZoneId;
import java.util.Optional;
import com.javitronics.javitronics.entity.CompraEntity;
import com.javitronics.javitronics.entity.FacturaEntity;
import com.javitronics.javitronics.entity.PedidoEntity;
import com.javitronics.javitronics.entity.ProductoEntity;
import com.javitronics.javitronics.entity.ProveedorEntity;
import com.javitronics.javitronics.entity.TipoproductoEntity;
import com.javitronics.javitronics.entity.TipoUsuarioEntity;
import com.javitronics.javitronics.entity.UsuarioEntity;
import com.javitronics.javitronics.helper.RandomHelper;
import com.javitronics.javitronics.repository.CompraRepository;
import com.javitronics.javitronics.repository.FacturaRepository;
import com.javitronics.javitronics.repository.PedidoRepository;
import com.javitronics.javitronics.repository.ProductoRepository;
import com.javitronics.javitronics.repository.ProveedorRepository;
import com.javitronics.javitronics.repository.TipoproductoRepository;
import com.javitronics.javitronics.repository.TipoUsuarioRepository;
import com.javitronics.javitronics.repository.UsuarioRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author JAVIER
 */

    @Service
public class FillService {

    @Autowired
    TipoproductoRepository oTipoproductoRepository;

    @Autowired
    ProductoRepository oProductoRepository;

    @Autowired
    CompraRepository oCompraRepository;

    @Autowired
    TipoUsuarioRepository oTipousuarioRepository;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    FacturaRepository oFacturaRepository;
    
    @Autowired
    ProveedorRepository oProveedorRepository;
    
    @Autowired
    PedidoRepository oPedidoRepository;

    public String getProducto01c() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 10)) {
            case 1:
                nombre = "Productos";
                break;
            case 2:
                nombre = "Accesorios";
                break;
            case 3:
                nombre = "Herramientas";
                break;
            case 4:
                nombre = "Artículos";
                break;
            case 5:
                nombre = "Aperajes";
                break;
            case 6:
                nombre = "Instrumentos";
                break;
            case 7:
                nombre = "Complementos";
                break;
            case 8:
                nombre = "Recambios";
                break;
            case 9:
                nombre = "Mercancías";
                break;
            case 10:
                nombre = "Suplementos";
                break;
        }
        return nombre;
    }

    public String getProducto01p() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 10)) {
            case 1:
                nombre = "Difusor";
                break;
            case 2:
                nombre = "Adaptador";
                break;
            case 3:
                nombre = "Pompa";
                break;
            case 4:
                nombre = "Porcelana";
                break;
            case 5:
                nombre = "Radiador";
                break;
            case 6:
                nombre = "Batería";
                break;
            case 7:
                nombre = "Repetidor";
                break;
            case 8:
                nombre = "Base";
                break;
            case 9:
                nombre = "Cartucho";
                break;
            case 10:
                nombre = "Cable";
                break;
        }
        return nombre;
    }

    public String getProducto02() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 9)) {
            case 1:
                nombre += " de limpieza";
                break;
            case 2:
                nombre += " de reaparación";
                break;
            case 3:
                nombre += " de puesta a punto";
                break;
            case 4:
                nombre += " de servicio";
                break;
            case 5:
                nombre += " de asistencia";
                break;
            case 6:
                nombre += " de apoyo";
                break;
            case 7:
                nombre += " de franqueo";
                break;
            case 8:
                nombre += " de refuerzo";
                break;
            case 9:
                nombre += " de protección";
                break;
        }
        return nombre;
    }

    public String getProducto03() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 7)) {
            case 1:
                nombre += " de hogar";
                break;
            case 2:
                nombre += " de automoción";
                break;
            case 3:
                nombre += " de joyería";
                break;
            case 4:
                nombre += " de moda";
                break;
            case 5:
                nombre += " de jardín";
                break;
            case 6:
                nombre += " de juguetes";
                break;
            case 7:
                nombre += " de electrónica";
                break;
        }
        return nombre;
    }

    public String getProducto04() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 9)) {
            case 1:
                nombre += " para amantes del cine";
                break;
            case 2:
                nombre += " para mascotas";
                break;
            case 3:
                nombre += " industrial";
                break;
            case 4:
                nombre += " para el bricolage";
                break;
            case 5:
                nombre += " para la purificación";
                break;
            case 6:
                nombre += " de amplio espectro";
                break;
            case 7:
                nombre += " de primera categoría";
                break;
            case 8:
                nombre += " de alta granularidad";
                break;
            case 9:
                nombre += " resistente al agua";
                break;
        }
        return nombre;
    }

    private String getProductoLast() {
        String nombre = "";
        switch (RandomHelper.getRandomInt(1, 21)) {

            case 1:
                nombre += " de color negro";
                break;
            case 2:
                nombre += " de color blanco";
                break;
            case 3:
                nombre += " de color gris";
                break;
            case 4:
                nombre += " de color verde";
                break;
            case 5:
                nombre += " de color rojo";
                break;
            case 6:
                nombre += " de color azul";
                break;
            case 7:
                nombre += " de color amarillo";
                break;
            case 8:
                nombre += " de color marrón";
                break;
            case 9:
                nombre += " de color rosa";
                break;
            case 10:
                nombre += " polivalente";
                break;
            case 11:
                nombre += " ambivalente";
                break;
            case 12:
                nombre += " versátil";
                break;
            case 13:
                nombre += " adaptable";
                break;
            case 14:
                nombre += " variable";
                break;
            case 15:
                nombre += " oscilante";
                break;
            case 16:
                nombre += " basculante";
                break;
            case 17:
                nombre += " robusto";
                break;
            case 18:
                nombre += " super eficiente";
                break;
            case 19:
                nombre += " ultra potente";
                break;
            case 20:
                nombre += " dinámico";
                break;
            case 21:
                nombre += " consistente";
                break;
        }
        return nombre;
    }    
    
    public Long tipoproductoFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            TipoproductoEntity oTipoproductoEntity = new TipoproductoEntity();
            oTipoproductoEntity.setNombre(this.getProducto01c() + this.getProducto02() + this.getProducto03() + this.getProducto04());
            oTipoproductoRepository.save(oTipoproductoEntity);
        }
        return cantidad;
    }

    public Long productoFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            ProductoEntity oProductoEntity = new ProductoEntity();
            oProductoEntity.setCodigo(RandomHelper.getRandomHexString(RandomHelper.getRandomInt(5, 10)).toUpperCase());
            oProductoEntity.setDescuento(RandomHelper.getRandomInt(0, 3));
            oProductoEntity.setExistencias(RandomHelper.getRandomInt(0, 1000));
            oProductoEntity.setNombre(this.getProducto01p() + this.getProducto02() + this.getProducto03() + this.getProductoLast());
            Optional<TipoproductoEntity> optionalTipoproductoEntity = oTipoproductoRepository.findById(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            TipoproductoEntity oTipoproductoEntity = optionalTipoproductoEntity.get();  
            oProductoEntity.setTipoproducto(oTipoproductoEntity);                        
            oProductoEntity.setPrecio(RandomHelper.getRadomDouble(1, 3000));
            oProductoRepository.save(oProductoEntity);
        }
        return cantidad;
    }

    public Long tipousuarioFill() {

        TipoUsuarioEntity oTipousuarioEntity = new TipoUsuarioEntity();
        oTipousuarioEntity.setNombre("Administrador");
        oTipousuarioRepository.save(oTipousuarioEntity);

        oTipousuarioEntity = new TipoUsuarioEntity();
        oTipousuarioEntity.setNombre("Cliente");
        oTipousuarioRepository.save(oTipousuarioEntity);

        return 2L;
        
    }

    public Long usuarioFill(Long cantidad) {

        String[] nombres = {"Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
            "Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
            "Caritina", "Carlota", "Baltazar"};
        String[] apellidos = {"Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
            "Carion", "Castiyo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
            "Grigalva"};

        for (int i = 1; i <= cantidad; i++) {

            UsuarioEntity oUsuarioEntity = new UsuarioEntity();

            oUsuarioEntity.setDni(String.valueOf(RandomHelper.getRandomInt(11111111, 99999999) + String.valueOf(RandomHelper.getRadomChar()).toUpperCase()));
            String nombre = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))];
            String apellido1 = apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            String apellido2 = apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            oUsuarioEntity.setNombre(nombre);
            oUsuarioEntity.setApellido1(apellido1);
            oUsuarioEntity.setApellido2(apellido2);
            //Maybe esta bien
            oUsuarioEntity.setLogin(nombre.substring(0, 2).toLowerCase() + apellido1.substring(0, 2).toLowerCase() + apellido2.substring(0, 2).toLowerCase() + String.valueOf(RandomHelper.getRandomInt(1, 10)));
            oUsuarioEntity.setPassword("da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04");
            oUsuarioEntity.setEmail(nombre + apellido1.charAt(0) + "@javitronics.net");
            oUsuarioEntity.setDescuento(0);
            TipoUsuarioEntity oTipousuarioEntity = new TipoUsuarioEntity();
            oTipousuarioEntity.setId(2L);
            oUsuarioEntity.setTipoUsuario(oTipousuarioEntity);
            oUsuarioEntity.setToken("");
            oUsuarioEntity.setValidado(true);
            oUsuarioEntity.setActivo(true);

            oUsuarioRepository.save(oUsuarioEntity);
        }
        return cantidad;
    }

    public Long compraFill(Long cantidad) {

        for (int i = 1; i <= cantidad; i++) {
            CompraEntity oCompraEntity = new CompraEntity();
            oCompraEntity.setCantidad(RandomHelper.getRandomInt(1, 5));
            oCompraEntity.setPrecio(RandomHelper.getRadomDouble(1, 200));
            oCompraEntity.setFecha(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            oCompraEntity.setDescuento_usuario(RandomHelper.getRandomInt(0, 2));
            oCompraEntity.setDescuento_producto(RandomHelper.getRandomInt(0, 2));            
            Optional<FacturaEntity> optionalFacturaEntity = oFacturaRepository.findById(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            FacturaEntity oFacturaEntity = optionalFacturaEntity.get();  
            oCompraEntity.setFactura(oFacturaEntity);
            Optional<ProductoEntity> optionalProductoEntity = oProductoRepository.findById(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            ProductoEntity oProductoEntity = optionalProductoEntity.get();
            oCompraEntity.setProducto(oProductoEntity);
            oCompraRepository.save(oCompraEntity);
        }
        return cantidad;
    }

    public Long facturaFill(Long cantidad) {

        int[] ivas = {4, 10, 21};

        for (int i = 1; i <= cantidad; i++) {
            int iva = ivas[(int) (Math.floor(Math.random() * ((ivas.length - 1) - 0 + 1) + 0))];
            FacturaEntity oFacturaEntity = new FacturaEntity();
            oFacturaEntity.setFecha(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            oFacturaEntity.setIva(iva);           
            Optional<UsuarioEntity> optionalUsuarioEntity = oUsuarioRepository.findById(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            UsuarioEntity oUsuarioEntity = optionalUsuarioEntity.get();                          
            oFacturaEntity.setUsuario(oUsuarioEntity);
            oFacturaEntity.setPagado(true);
            oFacturaRepository.save(oFacturaEntity);
        }
        return cantidad;

    }

    
    public Long pedidoFill(Long cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            PedidoEntity oPedidoEntity = new PedidoEntity();
            oPedidoEntity.setFecha(RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            oPedidoEntity.setCantidad(RandomHelper.getRandomInt(0, 1000));
            Optional<ProductoEntity> optionalProductoEntity = oProductoRepository.findById(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            ProductoEntity oProductoEntity = optionalProductoEntity.get();  
            oPedidoEntity.setProducto((Set<ProductoEntity>) oProductoEntity);
            oPedidoRepository.save(oPedidoEntity);
        }
         return cantidad;
   
    }
    
    public Long proveedorFill(Long cantidad) {
        String[] nombre1 = {"Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
            "Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
            "Caritina", "Carlota", "Baltazar"};
        String[] apellidos1 = {"Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
            "Carion", "Castiyo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
            "Grigalva"};
        String[] direccion1 = {"Valencia", "Requena", "Utiel", "Cullera", "Oliva", "Alicante"};
        
       
        for (int i = 1; i <= cantidad; i++) {

            ProveedorEntity oProveedorEntity = new ProveedorEntity();

            String nombre = nombre1[(int) (Math.floor(Math.random() * ((nombre1.length - 1) - 0 + 1) + 0))];
            String apellido1 = apellidos1[(int) (Math.floor(Math.random() * ((apellidos1.length - 1) - 0 + 1) + 0))];
            String apellido2 = apellidos1[(int) (Math.floor(Math.random() * ((apellidos1.length - 1) - 0 + 1) + 0))];
            oProveedorEntity.setNombre(nombre);
            oProveedorEntity.setApellidos(apellido1);
            oProveedorEntity.setEmail(nombre + apellido1.charAt(0) + "@javitronics.net");
            String direccion = direccion1[(int) (Math.floor(Math.random() * ((direccion1.length - 1) - 0 + 1) + 0))];
            oProveedorEntity.setDireccion(direccion);
            Optional<PedidoEntity> optionalPedidoEntity = oPedidoRepository.findById(Long.valueOf(RandomHelper.getRandomInt(1, 100)));
            PedidoEntity oPedidoEntity = optionalPedidoEntity.get();  
            oProveedorEntity.setPedido((Set<PedidoEntity>) oPedidoEntity);     
            oProveedorRepository.save(oProveedorEntity);
        }
        
        
        
         return cantidad;
   
    }
    
}
