
//__________________________________________________
/**
 *
 * @author Rober, Angel, Diego, Santiago, Silvano
 */
//__________________________________________________
import Clases.*;
import Clases.Nodo;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        setIconImage(getIconImage());
        this.setLocationRelativeTo(null);//Centrar al iniciar

        //estetica de los botones
        BotonGaleria.putClientProperty("Button.arc", 0);
        BotonAlbum.putClientProperty("Button.arc", 0);
        BotonConfi.putClientProperty("Button.arc", 0);

    }
//__________________________________________________
    //Icono para el Jframe

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Icono.png"));
        return retValue;
    }
//__________________________________________________

    //Resto de codigo por aqui
    //Dejar de meter codigo aqui
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
    //Atributos
    Nodo actual = null, inicio = null, fin = null;
    String rutaImagen;
    int smileyCodePoint;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Metodos
    void cargarImagen() {
        try {
            JFileChooser explorador = new JFileChooser();
            explorador.addChoosableFileFilter(new FileNameExtensionFilter("Imagen", "png", "jpg", "jpeg"));
            explorador.showOpenDialog(null);
            File auxFile = explorador.getSelectedFile();

            if (auxFile != null) { // Verifica que se haya seleccionado un archivo
                rutaImagen = auxFile.getAbsolutePath();
                ImageIcon icono = new ImageIcon(rutaImagen);

                // Obtener el tamaño del JLabel
                int ancho = ImagenInicio1.getWidth();
                int alto = ImagenInicio1.getHeight();

                // Escalar la imagen al tamaño del JLabel
                Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                ImagenInicio1.setIcon(new ImageIcon(imgEscalada));
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    void actualizarTexto() {
        if (listaVacia() || actual == null) {
            System.out.println("Lista vacía o actual es null.");
            return;
        }

        System.out.println("Mostrando nodo: " + actual.getNombreImagen()); // Ver qué nodo se está mostrando
        TxtNombreImagen.setText(actual.getNombreImagen());
        TxtDescripcionImagen.setText(actual.getDescripcion());
        TxtEmoji.setText(actual.getEmoji());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        TxtMostraFecha.setText(formato.format(actual.getFecha()));
    }

    void actualizarImagen() {

        if (listaVacia()) {
//            ImagenInicio1.setText("Lista Vacia");
//            ImagenInicio1.setIcon("/Imagenes/SinImagenIconoGrande.png"); // Asegurar que no haya imagen si la lista está vacía
            Imagen1.setIcon(null);
            Imagen2.setIcon(null);
            Imagen3.setIcon(null);
            Imagen4.setIcon(null);
            Imagen5.setIcon(null);
            Imagen6.setIcon(null);
            Imagen7.setIcon(null);
            Imagen8.setIcon(null);

        } else {

            // Redimensionar imagen antes de asignarla
            rutaImagen = actual.getRutaImagen();

            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                ImageIcon icono = new ImageIcon(rutaImagen);
                //Imagen inicio
                Image imgEscalada1 = icono.getImage().getScaledInstance(ImagenInicio1.getWidth(), ImagenInicio1.getHeight(), Image.SCALE_SMOOTH);
                ImagenInicio1.setIcon(new ImageIcon(imgEscalada1));

            } else {
                ImagenInicio1.setIcon(null);

            }

        }

    }

    //Hacemos una funcion para crear un popmenu en el boton de emoji
    void menuEmojis() {
        // Limpiar el menú antes de agregar nuevos elementos
        MenuEmojis.removeAll();

        //Se agrega los items 
        JMenuItem feliz = new JMenuItem(getIcon("/Imagenes/Emojis/feliz.png", 25, 25));
        JMenuItem triste = new JMenuItem(getIcon("/Imagenes/Emojis/triste.png", 25, 25));
        JMenuItem enojado = new JMenuItem(getIcon("/Imagenes/Emojis/enojado.png", 25, 25));
        JMenuItem like = new JMenuItem(getIcon("/Imagenes/Emojis/like.png", 25, 25));
        JMenuItem risa = new JMenuItem(getIcon("/Imagenes/Emojis/risa.png", 25, 25));
        JMenuItem enamorado = new JMenuItem(getIcon("/Imagenes/Emojis/enamorado.png", 25, 25));

        //Se agrega en el Popmenu los items creados
        MenuEmojis.add(feliz);
        MenuEmojis.add(triste);
        MenuEmojis.add(enojado);
        MenuEmojis.add(like);
        MenuEmojis.add(risa);
        MenuEmojis.add(enamorado);

        //Se le agrega el popmenu al boton al darle clicl derecho
        BotonEmoji.setComponentPopupMenu(MenuEmojis);

        //Hacemos las acciones de cada item, o emoji
        feliz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smileyCodePoint = 0x1F600; // Código Unicode del emoji feliz
                String textWithEmoji = new String(Character.toChars(smileyCodePoint));

                // Asignar el texto con emoji al JTextField
                TxtEmoji.setText(textWithEmoji);
            }
        });

        triste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smileyCodePoint = 0x1F62D; // Código Unicode del emoji triste
                String textWithEmoji = new String(Character.toChars(smileyCodePoint));

                // Asignar el texto con emoji al JTextField
                TxtEmoji.setText(textWithEmoji);
            }
        });

        enojado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smileyCodePoint = 0x1F621; // Código Unicode del emoji enojado
                String textWithEmoji = new String(Character.toChars(smileyCodePoint));

                // Asignar el texto con emoji al JTextField
                TxtEmoji.setText(textWithEmoji);
            }
        });

        like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smileyCodePoint = 0x1F44D; // Código Unicode del emoji like
                String textWithEmoji = new String(Character.toChars(smileyCodePoint));

                // Asignar el texto con emoji al JTextField
                TxtEmoji.setText(textWithEmoji);
            }
        });

        risa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smileyCodePoint = 0x1F602; // Código Unicode del emoji risa
                String textWithEmoji = new String(Character.toChars(smileyCodePoint));

                // Asignar el texto con emoji al JTextField
                TxtEmoji.setText(textWithEmoji);
            }
        });

        enamorado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smileyCodePoint = 0x1F60D; // Código Unicode del emoji enamorado
                String textWithEmoji = new String(Character.toChars(smileyCodePoint));
                // Asignar el texto con emoji al JTextField
                TxtEmoji.setText(textWithEmoji);

            }
        });

    }

    //Hacemos una funcion para crear una ruta de imagenes
    public Icon getIcon(String ruta, int ancho, int altura) {
        Icon icono = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(ancho, altura, 0));
        return icono;
    }

    boolean listaVacia() {
        return inicio == null;
    }

    void Insertar() {

        Date fechaSeleccionada = (Date) SpinnerFecha.getValue();
        Nodo nuevo = new Nodo(TxtNombreImagen.getText(), TxtDescripcionImagen.getText(), rutaImagen, TxtEmoji.getText(), fechaSeleccionada, null, null);

        if (listaVacia()) {
            inicio = fin = actual = nuevo;
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
        } else {
            nuevo.setAnterior(fin);
            nuevo.setSiguiente(inicio);
            fin.setSiguiente(nuevo);
            inicio.setAnterior(nuevo);
            fin = nuevo;
        }
        System.out.println("=============================");
        System.out.println("Nodo Agregado al Final");
        actual = inicio;  // Opcional, depende cómo quieras controlar el visor

        //Resetear ruta de la imagen
        rutaImagen = null;
        //Limpia la entrada de texto de las casillas
        TxtNombreImagen.setText("");
        TxtDescripcionImagen.setText("");
        TxtEmoji.setText("");
    }

    void Borrar() {
        try {
            if (listaVacia()) {
                System.out.println("La lista esta vacia");
                TxtNombreImagen.setText("Esta Vacio");
                TxtDescripcionImagen.setText("Esta Vacio");
                TxtEmoji.setText("Esta Vacia");
                TxtMostraFecha.setText("Esta Vacia");
                JOptionPane.showMessageDialog(null, "No se puede eliminar, la lista esta vacia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;

            }

            if (inicio == fin) {  // Solo hay un nodo
                inicio = fin = actual = null;
            } else {
                fin = fin.getAnterior();  // Retrocedemos el puntero 'fin' al nodo anterior
                fin.setSiguiente(inicio); // El nuevo 'fin' apunta al 'inicio' (circularidad)
                inicio.setAnterior(fin);  // El 'inicio' apunta al nuevo 'fin' (circularidad)
            }

            actual = inicio;  // Ajustamos el puntero actual al nuevo inicio
            //Limpia la entrada de texto de las casillas
            TxtNombreImagen.setText("");
            TxtDescripcionImagen.setText("");
            TxtEmoji.setText("");
            TxtMostraFecha.setText("");
            ImagenInicio1.setIcon(null);
            System.out.println("Ultimo nodo eliminado");
            JOptionPane.showMessageDialog(null, "Ultimo nodo eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "La lista esta vacia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    void EliminarTodo() {
        if (listaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay elementos en la lista", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            /* En lugar de recorer nodo por nodo simplemente asignamos null a inicio y al fin lo que vacia la lista 
            Se muestra un mensaje de confirmacion    
             */
            inicio = null;
            fin = null;
            JOptionPane.showMessageDialog(null, "Lista eliminada con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        String temporalImagen = "/Imagenes/SinImagenIconoGrande.png";

        if (temporalImagen != null && !temporalImagen.isEmpty()) {
            ImageIcon icono = new ImageIcon(temporalImagen);
            Image imgEscalada = icono.getImage().getScaledInstance(ImagenInicio1.getWidth(), ImagenInicio1.getHeight(), Image.SCALE_SMOOTH);
            ImagenInicio1.setIcon(new ImageIcon(imgEscalada)); // Asignar imagen escalada
        } else {
            ImagenInicio1.setIcon(null); // Si la ruta es nula, limpiar el JLabel
        }
    }

    // Metodo para avanzar al siguiente nodo
    public void Avanzar() {
        if (listaVacia()) {
            System.out.println("Lista Vacia");
            JOptionPane.showMessageDialog(null, "Lista vacía");
            return;
        }
        if (actual == null) {
            actual = inicio;  // Por seguridad
        }
        actual = actual.getSiguiente();
        System.out.println("Nombre del la Imagen Avanzando: " + actual.getNombreImagen());
        System.out.println("Nombre del la Descripcion Avanzando: " + actual.getDescripcion());
        System.out.println("Nombre del Emoji Avanzando: " + actual.getEmoji());
        actualizarTexto();  // Mostrar nueva imagen
    }

    public void Retroceder() {
        if (listaVacia()) {
            JOptionPane.showMessageDialog(null, "Lista vacía");
            return;
        }
        if (actual == null) {
            actual = inicio;  // Por seguridad
        }
        actual = actual.getAnterior();
        System.out.println("Nombre del la Imagen Avanzando: " + actual.getNombreImagen());
        System.out.println("Nombre del la Descripcion Avanzando: " + actual.getDescripcion());
        System.out.println("Nombre del Emoji Avanzando: " + actual.getEmoji());
        actualizarTexto();  // Mostrar nueva imagen
    }

    // Metodo para avanzar hasta el final
    public Nodo AvanzarFinal() {
        System.out.println("Avanzando Hasta el Final...");
        if (listaVacia()) {
            System.out.println("Lista Vacia");
            JOptionPane.showMessageDialog(null, "Lista vacía");
            return null;
        }
        actual = fin;// Mover el puntero al final del nodo
        System.out.println("Nombre del la Imagen Avanzando: " + actual.getNombreImagen());
        System.out.println("Nombre del la Descripcion Avanzando: " + actual.getDescripcion());
        System.out.println("Nombre del Emoji Avanzando: " + actual.getEmoji());
        return fin;
    }

    // Metodo para avanzar hasta el inicio
    public Nodo AvanzarInicio() {
        System.out.println("Avanzando Hasta el Inicio...");
        if (listaVacia()) {
            System.out.println("Lista Vacia");
            JOptionPane.showMessageDialog(null, "Lista vacía");
            return null;
        }
        actual = inicio; // Mover el puntero al primer nodo
        System.out.println("Nombre del la Imagen Avanzando: " + actual.getNombreImagen());
        System.out.println("Nombre del la Descripcion Avanzando: " + actual.getDescripcion());
        System.out.println("Nombre del Emoji Avanzando: " + actual.getEmoji());
        return inicio;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuEmojis = new javax.swing.JPopupMenu();
        PanelBase = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BotonAlbum = new javax.swing.JButton();
        BotonGaleria = new javax.swing.JButton();
        BotonConfi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        BotonLimpiarBuffer = new javax.swing.JButton();
        LNombrePNG = new javax.swing.JLabel();
        LDescPNG = new javax.swing.JLabel();
        TxtNombreImagen = new javax.swing.JTextField();
        LFecha = new javax.swing.JLabel();
        SpinnerFecha = new javax.swing.JSpinner();
        BotonBorrarImage1 = new javax.swing.JButton();
        BotonImportarImage1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        TxtDescripcionImagen = new javax.swing.JTextField();
        LFecha1 = new javax.swing.JLabel();
        BotonEmoji = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        BotonCargarIma1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        GaleriaInsertar = new javax.swing.JPanel();
        TxtEmoji = new javax.swing.JTextField();
        TxtMostraFecha = new javax.swing.JTextField();
        ImagenInicio1 = new javax.swing.JLabel();
        BotonIrInicio = new javax.swing.JButton();
        BotonRetroceder = new javax.swing.JButton();
        BotonAvanzar = new javax.swing.JButton();
        BotonFinal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Album = new javax.swing.JPanel();
        Imagen4 = new javax.swing.JLabel();
        Imagen2 = new javax.swing.JLabel();
        Imagen3 = new javax.swing.JLabel();
        Imagen1 = new javax.swing.JLabel();
        Imagen5 = new javax.swing.JLabel();
        Imagen6 = new javax.swing.JLabel();
        Imagen7 = new javax.swing.JLabel();
        Imagen8 = new javax.swing.JLabel();
        Configuracion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BtnGuia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GaleriaRobocop");
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImages(null);
        setName("BaseFrame"); // NOI18N
        setSize(new java.awt.Dimension(1280, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelBase.setBackground(new java.awt.Color(255, 255, 255));
        PanelBase.setMaximumSize(new java.awt.Dimension(1000, 1000));
        PanelBase.setMinimumSize(new java.awt.Dimension(1280, 720));
        PanelBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(75, 19, 79));
        kGradientPanel1.setkStartColor(new java.awt.Color(201, 75, 75));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        kGradientPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 96, 218, 20));

        BotonAlbum.setBackground(new java.awt.Color(102, 102, 102));
        BotonAlbum.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonAlbum.setForeground(new java.awt.Color(255, 255, 255));
        BotonAlbum.setText("Album");
        BotonAlbum.setContentAreaFilled(false);
        BotonAlbum.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAlbumActionPerformed(evt);
            }
        });
        kGradientPanel1.add(BotonAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 230, 70));

        BotonGaleria.setBackground(new java.awt.Color(102, 102, 102));
        BotonGaleria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonGaleria.setForeground(new java.awt.Color(255, 255, 255));
        BotonGaleria.setText("Datos-Galeria");
        BotonGaleria.setBorderPainted(false);
        BotonGaleria.setContentAreaFilled(false);
        BotonGaleria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonGaleria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGaleriaActionPerformed(evt);
            }
        });
        kGradientPanel1.add(BotonGaleria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 70));

        BotonConfi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonConfi.setForeground(new java.awt.Color(255, 255, 255));
        BotonConfi.setText("Creditos");
        BotonConfi.setBorderPainted(false);
        BotonConfi.setContentAreaFilled(false);
        BotonConfi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonConfi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonConfiActionPerformed(evt);
            }
        });
        kGradientPanel1.add(BotonConfi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 230, 80));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoS.png"))); // NOI18N
        jLabel7.setText("Fotos");
        kGradientPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, 50));

        PanelBase.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 720));

        BotonLimpiarBuffer.setBackground(new java.awt.Color(255, 51, 51));
        BotonLimpiarBuffer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BotonLimpiarBuffer.setForeground(new java.awt.Color(255, 255, 255));
        BotonLimpiarBuffer.setText("Limpiar");
        BotonLimpiarBuffer.setBorderPainted(false);
        BotonLimpiarBuffer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonLimpiarBuffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiarBufferActionPerformed(evt);
            }
        });
        PanelBase.add(BotonLimpiarBuffer, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 110, 90, 20));

        LNombrePNG.setBackground(new java.awt.Color(0, 0, 0));
        LNombrePNG.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LNombrePNG.setText("Nombre de la Imagen:");
        PanelBase.add(LNombrePNG, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 190, 30));

        LDescPNG.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LDescPNG.setText("Descripcion:");
        PanelBase.add(LDescPNG, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 110, -1));

        TxtNombreImagen.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        PanelBase.add(TxtNombreImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 220, 30));

        LFecha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LFecha.setText("Limpiar Texto");
        PanelBase.add(LFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 80, 120, -1));

        SpinnerFecha.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        SpinnerFecha.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1742436969165L), null, null, java.util.Calendar.DAY_OF_MONTH));
        SpinnerFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelBase.add(SpinnerFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 210, 30));

        BotonBorrarImage1.setBackground(new java.awt.Color(248, 155, 155));
        BotonBorrarImage1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonBorrarImage1.setForeground(new java.awt.Color(255, 255, 255));
        BotonBorrarImage1.setText("Borrar Imagen");
        BotonBorrarImage1.setBorderPainted(false);
        BotonBorrarImage1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonBorrarImage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBorrarImage1ActionPerformed(evt);
            }
        });
        PanelBase.add(BotonBorrarImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 220, 50));

        BotonImportarImage1.setBackground(new java.awt.Color(166, 220, 149));
        BotonImportarImage1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonImportarImage1.setForeground(new java.awt.Color(255, 255, 255));
        BotonImportarImage1.setText("Insertar Imagen");
        BotonImportarImage1.setBorderPainted(false);
        BotonImportarImage1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonImportarImage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonImportarImage1ActionPerformed(evt);
            }
        });
        PanelBase.add(BotonImportarImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 220, 50));

        jSeparator2.setAlignmentX(1.0F);
        jSeparator2.setAlignmentY(1.0F);
        PanelBase.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 900, 20));

        TxtDescripcionImagen.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        PanelBase.add(TxtDescripcionImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 220, 30));

        LFecha1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LFecha1.setText("Colocar Fecha");
        PanelBase.add(LFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 120, -1));

        BotonEmoji.setBackground(new java.awt.Color(224, 224, 120));
        BotonEmoji.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonEmoji.setForeground(new java.awt.Color(255, 255, 255));
        BotonEmoji.setText("Emojis");
        BotonEmoji.setBorderPainted(false);
        BotonEmoji.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonEmoji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEmojiActionPerformed(evt);
            }
        });
        PanelBase.add(BotonEmoji, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 220, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonCargarIma1.setBackground(new java.awt.Color(229, 153, 172));
        BotonCargarIma1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonCargarIma1.setForeground(new java.awt.Color(255, 255, 255));
        BotonCargarIma1.setText("Cargar Imagen");
        BotonCargarIma1.setBorderPainted(false);
        BotonCargarIma1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonCargarIma1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCargarIma1ActionPerformed(evt);
            }
        });
        jPanel2.add(BotonCargarIma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 200, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel8.setText("Menu de Emojis: Click Izquierdo ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 160, -1));

        PanelBase.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 940, 240));

        GaleriaInsertar.setBackground(new java.awt.Color(255, 255, 255));
        GaleriaInsertar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtEmoji.setEditable(false);
        TxtEmoji.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        TxtEmoji.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtEmoji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtEmojiActionPerformed(evt);
            }
        });
        GaleriaInsertar.add(TxtEmoji, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 30));

        TxtMostraFecha.setEditable(false);
        GaleriaInsertar.add(TxtMostraFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 180, 30));

        ImagenInicio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SinImagenIconoGrande.png"))); // NOI18N
        GaleriaInsertar.add(ImagenInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 270, 270));

        BotonIrInicio.setBackground(new java.awt.Color(221, 235, 234));
        BotonIrInicio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonIrInicio.setText("<<");
        BotonIrInicio.setBorderPainted(false);
        BotonIrInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonIrInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIrInicioActionPerformed(evt);
            }
        });
        GaleriaInsertar.add(BotonIrInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 80, 60));

        BotonRetroceder.setBackground(new java.awt.Color(221, 235, 234));
        BotonRetroceder.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonRetroceder.setText("<");
        BotonRetroceder.setBorderPainted(false);
        BotonRetroceder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRetrocederActionPerformed(evt);
            }
        });
        GaleriaInsertar.add(BotonRetroceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 80, 60));

        BotonAvanzar.setBackground(new java.awt.Color(221, 235, 234));
        BotonAvanzar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonAvanzar.setText(">");
        BotonAvanzar.setBorderPainted(false);
        BotonAvanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAvanzarActionPerformed(evt);
            }
        });
        GaleriaInsertar.add(BotonAvanzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 80, 60));

        BotonFinal.setBackground(new java.awt.Color(221, 235, 234));
        BotonFinal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonFinal.setText(">>");
        BotonFinal.setBorderPainted(false);
        BotonFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonFinalActionPerformed(evt);
            }
        });
        GaleriaInsertar.add(BotonFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 80, 60));

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Borrrar Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        GaleriaInsertar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 430, 120, 40));

        jTabbedPane1.addTab("tab1", GaleriaInsertar);

        Album.setBackground(new java.awt.Color(255, 255, 255));
        Album.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Imagen4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 180, 210));

        Imagen2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 180, 210));

        Imagen3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 180, 210));

        Imagen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 180, 210));

        Imagen5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 180, 210));

        Imagen6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 180, 210));

        Imagen7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 180, 210));

        Imagen8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Album.add(Imagen8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 180, 210));

        jTabbedPane1.addTab("tab2", Album);

        Configuracion.setBackground(new java.awt.Color(255, 255, 255));
        Configuracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("-Diego Armando Naveja Lopez  ");
        Configuracion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 390, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Creditos");
        Configuracion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("-Angel Alexis Serrano Hernandez");
        Configuracion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 390, 60));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("-Roberto Perez Zamora");
        Configuracion.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 390, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("-Silvano Joset Valdivia Franco  ");
        Configuracion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 390, 60));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("-Santiago Jiménez García  ");
        Configuracion.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 390, 60));

        BtnGuia.setBackground(new java.awt.Color(0, 0, 51));
        BtnGuia.setFont(new java.awt.Font("ROG Fonts", 1, 36)); // NOI18N
        BtnGuia.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoAsistencia.png"))); // NOI18N
        BtnGuia.setText("Guia");
        BtnGuia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuiaActionPerformed(evt);
            }
        });
        Configuracion.add(BtnGuia, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 300, 170));

        jTabbedPane1.addTab("tab3", Configuracion);

        PanelBase.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 940, 520));

        getContentPane().add(PanelBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Acciones de los Botones de la parte izquierda, Boton Album
    private void BotonAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAlbumActionPerformed

        jTabbedPane1.setSelectedIndex(1); // Cambia al segundo panel 
        JOptionPane.showMessageDialog(null, "No hay presupuesto  :( ", "Informacion", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_BotonAlbumActionPerformed

    //Acciones de los Botones de la parte izquierda, Boton Galeria
    private void BotonGaleriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGaleriaActionPerformed

        jTabbedPane1.setSelectedIndex(0); // Cambia al primer panel 
    }//GEN-LAST:event_BotonGaleriaActionPerformed

    //Acciones de los Botones de la parte izquierda, Boton Confi
    private void BotonConfiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonConfiActionPerformed

        jTabbedPane1.setSelectedIndex(2); // Cambia al tercer panel


    }//GEN-LAST:event_BotonConfiActionPerformed

    private void BotonLimpiarBufferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiarBufferActionPerformed

        //Limpia la entrada de texto de las casillas
        TxtNombreImagen.setText("");
        TxtDescripcionImagen.setText("");
        TxtEmoji.setText("");
        TxtMostraFecha.setText("");


    }//GEN-LAST:event_BotonLimpiarBufferActionPerformed

    private void BotonCargarIma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCargarIma1ActionPerformed

        cargarImagen();

    }//GEN-LAST:event_BotonCargarIma1ActionPerformed

    private void BotonBorrarImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBorrarImage1ActionPerformed

        Borrar();
        actualizarImagen();
        actualizarTexto();

    }//GEN-LAST:event_BotonBorrarImage1ActionPerformed

    private void BotonImportarImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonImportarImage1ActionPerformed

        try {
            if (TxtNombreImagen.getText().isEmpty() || TxtDescripcionImagen.getText().isEmpty() || TxtEmoji.getText().isEmpty() || rutaImagen == null) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre y selecciona una imagen", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                Insertar();
                actualizarImagen();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa el nombre y selecciona una imagen", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_BotonImportarImage1ActionPerformed

    private void TxtEmojiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtEmojiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEmojiActionPerformed

    private void BotonAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAvanzarActionPerformed

        Avanzar();
        actualizarImagen();
        actualizarTexto();

    }//GEN-LAST:event_BotonAvanzarActionPerformed

    private void BotonRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRetrocederActionPerformed

        Retroceder();
        actualizarImagen();
        actualizarTexto();

    }//GEN-LAST:event_BotonRetrocederActionPerformed

    private void BotonFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonFinalActionPerformed

        AvanzarFinal();
        actualizarImagen();
        actualizarTexto();


    }//GEN-LAST:event_BotonFinalActionPerformed

    private void BotonIrInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIrInicioActionPerformed

        AvanzarInicio();
        actualizarImagen();
        actualizarTexto();

    }//GEN-LAST:event_BotonIrInicioActionPerformed

    private void BotonEmojiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEmojiActionPerformed

        menuEmojis();

    }//GEN-LAST:event_BotonEmojiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        rutaImagen = null;
        //Limpia la entrada de texto de las casillas
        TxtNombreImagen.setText("");
        TxtDescripcionImagen.setText("");
        TxtEmoji.setText("");
        EliminarTodo();
        actualizarImagen();
        actualizarTexto();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void BtnGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuiaActionPerformed

        String link = "https://github.com/AngelSH10004/ProyectoIntegrador";

        try {
            // Usar Desktop.getDesktop() para abrir el enlace en el navegador por defecto
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(link));
        } catch (IOException e) {
            // Si ocurre un error, lo imprimimos

        }
    }//GEN-LAST:event_BtnGuiaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        try {
//            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatCyanLightIJTheme.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        UIManager.put("Button.arc", 999);
        UIManager.put("Component.arc", 999);
        UIManager.put("TextComponent.arc", 5);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Album;
    private javax.swing.JButton BotonAlbum;
    private javax.swing.JButton BotonAvanzar;
    private javax.swing.JButton BotonBorrarImage1;
    private javax.swing.JButton BotonCargarIma1;
    private javax.swing.JButton BotonConfi;
    private javax.swing.JButton BotonEmoji;
    private javax.swing.JButton BotonFinal;
    private javax.swing.JButton BotonGaleria;
    private javax.swing.JButton BotonImportarImage1;
    private javax.swing.JButton BotonIrInicio;
    private javax.swing.JButton BotonLimpiarBuffer;
    private javax.swing.JButton BotonRetroceder;
    private javax.swing.JButton BtnGuia;
    private javax.swing.JPanel Configuracion;
    private javax.swing.JPanel GaleriaInsertar;
    private javax.swing.JLabel Imagen1;
    private javax.swing.JLabel Imagen2;
    private javax.swing.JLabel Imagen3;
    private javax.swing.JLabel Imagen4;
    private javax.swing.JLabel Imagen5;
    private javax.swing.JLabel Imagen6;
    private javax.swing.JLabel Imagen7;
    private javax.swing.JLabel Imagen8;
    private javax.swing.JLabel ImagenInicio1;
    private javax.swing.JLabel LDescPNG;
    private javax.swing.JLabel LFecha;
    private javax.swing.JLabel LFecha1;
    private javax.swing.JLabel LNombrePNG;
    private javax.swing.JPopupMenu MenuEmojis;
    private javax.swing.JPanel PanelBase;
    private javax.swing.JSpinner SpinnerFecha;
    private javax.swing.JTextField TxtDescripcionImagen;
    private javax.swing.JTextField TxtEmoji;
    private javax.swing.JTextField TxtMostraFecha;
    private javax.swing.JTextField TxtNombreImagen;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
