package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuarios {
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String clave;
    private String rool;
    public static Usuarios usuarioActual = null;


    // Constructor vacío
    public Usuarios() {
    }

    // Constructor con parámetros
    public Usuarios(String nombre, String apellido, String correo, String username, String clave, String rool) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.username = username;
        this.clave = clave;
        this.rool = rool;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRool() {
        return rool;
    }

    public void setRool(String rool) {
        this.rool = rool;
    }
    
    public void insertarUsuarios(String nombre, String apellido, String email, String nickname, String clave, String roll){
        Conector con = new Conector();
        String sql = "INSERT INTO usuarios(nombre, apellido, correo ,username ,clave,roll) VALUES (?,?,?,?,?,?)";
            
        try(PreparedStatement ps = con.prepararStatement(sql)){
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4,nickname );
            ps.setString(5,clave );
            ps.setString(6, roll);
            ps.execute();

            System.out.println("Usuario registrado correctamente");
        }catch(SQLException e){
            System.out.println("Error insertando usuario " +e.getMessage());
        }finally{
            con.desconectar();
        }
    
    }
    
  public boolean verificarUsuario(String nickname, String clave) {
    Conector con = new Conector();
    String sql = "SELECT username, clave FROM usuarios WHERE username = ? AND clave = ?";

    try (PreparedStatement ps = con.prepararStatement(sql)) {
        ps.setString(1, nickname);
        ps.setString(2, clave);

        // Usamos el método ejecutarConsulta
        ResultSet rs = con.ejecutarConsulta(ps);

        if (rs.next()) {
            System.out.println("Usuario verificado correctamente.");
            return true;
        } else {
            System.out.println("Nickname o clave incorrectos.");
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar usuario: " + e.getMessage());
        return false;
    } finally {
        con.desconectar();
  
    }
  }
  
  
    public void obtenerDatosUsuario(String username) {
 
      Conector con = new Conector();
      String sql = "SELECT nombre, correo, roll, apellido  FROM usuarios WHERE username = ?";
      try (PreparedStatement ps =con.prepararStatement(sql)){
          ps.setString(1, username);
          ResultSet rs = con.ejecutarConsulta(ps);

          if (rs.next()) {
              setNombre(rs.getString("nombre"));
              setCorreo(rs.getString("correo"));
              setRool(rs.getString("roll")); 
              setApellido(rs.getString("apellido"));//
              Usuarios.usuarioActual = this;
              System.out.println("consulta ok");
            }
        } catch (SQLException e) {
          System.out.println("error" + e );
        }
      
    }
    
    public java.util.List<Usuarios> obtenerTodosLosUsuarios() {
        java.util.List<Usuarios> listaUsuarios = new java.util.ArrayList<>();
        Conector con = new Conector();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement ps = con.prepararStatement(sql)) {
            ResultSet rs = con.ejecutarConsulta(ps);

            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setUsername(rs.getString("username"));
                usuario.setRool(rs.getString("roll"));

                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        } finally {
            con.desconectar();
        }

        return listaUsuarios;
    }
    

}
