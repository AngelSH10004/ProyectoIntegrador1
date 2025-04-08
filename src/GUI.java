
//__________________________________________________
/**
 *
 * @author Rober, Angel, Diego, Santiago, Silvano
 */
//__________________________________________________
import Clases.*;
import Clases.Nodo;
//Si quieres implementar el diseño, importa la libreria de abajo
//import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
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
    boolean listaOrdenada = false;
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

        //decir que no esta ordenada
        listaOrdenada = false;
    }

    //Metod Buscar
    public void buscarBinario() {
        if (listaVacia()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Casos donde SÍ se permite buscar:
        // 1. Lista ordenada (para 2+ elementos) O 
        // 2. Solo hay 1 elemento (siempre está ordenado)
        boolean puedeBuscar = listaOrdenada || inicio == fin;
        
        // Verifica si la lista NO está ordenada
        if (!puedeBuscar) {
            JOptionPane.showMessageDialog(null, "La lista no está ordenada. Dale al Boton de Ordenar (De lo contrario te va rechazar como ella)",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreImagen = JOptionPane.showInputDialog("Ingrese el nombre de la imagen a buscar:");

        if (nombreImagen == null || nombreImagen.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        nombreImagen = nombreImagen.trim();

        // Realiza la búsqueda binaria (lista ya ordenada)
        Nodo encontrado = busquedaBinariaRecursiva(nombreImagen);

        if (encontrado != null) {
            actual = encontrado;
            actualizarTexto();
            actualizarImagen(false);

            String mensaje = "¡Imagen encontrada!\n"
                    + "Nombre: " + encontrado.getNombreImagen() + "\n"
                    + "Descripción: " + encontrado.getDescripcion() + "\n"
                    + "Fecha: " + encontrado.getFecha() + "\n"
                    + "Emoji: " + encontrado.getEmoji();

            JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "La imagen '" + nombreImagen + "' no se encontró.", "No encontrado", JOptionPane.WARNING_MESSAGE);
        }

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
                listaOrdenada = true;  // <-- Lista vacía se considera ordenada
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
    /**
     * Ordenamiento Marge Sort
     */
    // Método para ordenar la lista usando MergeSort
    void ordenarPorNombre() {
        if (listaVacia() || inicio == fin) {
            listaOrdenada = true;  // Lista vacía o con 1 elemento se considera ordenada
            return;
        }

        // Desconectamos la lista circular para ordenarla
        inicio.setAnterior(null);
        fin.setSiguiente(null);

        // Ordenamos la lista
        inicio = mergeSort(inicio);

        // Reconectamos la lista circular
        Nodo temp = inicio;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        fin = temp;
        inicio.setAnterior(fin);
        fin.setSiguiente(inicio);

        actual = inicio; // Actualizamos el nodo actual

        //decir que si esta ordenada
        listaOrdenada = true;

    }

// Método MergeSort para lista enlazada
    private Nodo mergeSort(Nodo cabeza) {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return cabeza;
        }

        // Dividimos la lista en dos
        Nodo mitad = obtenerMitad(cabeza);
        Nodo siguienteMitad = mitad.getSiguiente();
        mitad.setSiguiente(null);

        // Ordenamos recursivamente cada mitad
        Nodo izquierda = mergeSort(cabeza);
        Nodo derecha = mergeSort(siguienteMitad);

        // Combinamos las mitades ordenadas
        return merge(izquierda, derecha);
    }

// Método para obtener el nodo medio de la lista
    private Nodo obtenerMitad(Nodo cabeza) {
        if (cabeza == null) {
            return cabeza;
        }

        Nodo lento = cabeza;
        Nodo rapido = cabeza.getSiguiente();

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }

        return lento;
    }

// Método para combinar dos listas ordenadas
    private Nodo merge(Nodo a, Nodo b) {
        Nodo resultado = null;

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        // Comparamos los nombres de las imágenes
        if (a.getNombreImagen().compareToIgnoreCase(b.getNombreImagen()) <= 0) {
            resultado = a;
            resultado.setSiguiente(merge(a.getSiguiente(), b));
            resultado.getSiguiente().setAnterior(resultado);
        } else {
            resultado = b;
            resultado.setSiguiente(merge(a, b.getSiguiente()));
            resultado.getSiguiente().setAnterior(resultado);
        }

        return resultado;
    }

    // Modificación de actualizarImagen para mostrar ordenado cuando sea necesario
    void actualizarImagen(boolean ordenado) {
        if (listaVacia()) {
            Imagen1.setIcon(null);
            Imagen2.setIcon(null);
            Imagen3.setIcon(null);
            Imagen4.setIcon(null);
            Imagen5.setIcon(null);
            Imagen6.setIcon(null);
            Imagen7.setIcon(null);
            Imagen8.setIcon(null);
        } else {
            // Ordenar si es necesario
            if (ordenado) {
                ordenarPorNombre();
            }

//            // Mostrar imagen principal
//            rutaImagen = actual.getRutaImagen();
//            if (rutaImagen != null && !rutaImagen.isEmpty()) {
//                ImageIcon icono = new ImageIcon(rutaImagen);
//                setImagenEnLabel(icono, ImagenInicio1);
//            }
            // Array de labels
            JLabel[] labels = {
                Imagen1, Imagen2, Imagen3, Imagen4,
                Imagen5, Imagen6, Imagen7, Imagen8
            };

            // Empezar desde el nodo actual (o el inicio si actual es null)
            Nodo nodoActual = (actual != null) ? actual : inicio;
            int contador = 0;

            // Recorrer la lista y asignar imágenes
            while (nodoActual != null && contador < labels.length) {
                rutaImagen = nodoActual.getRutaImagen();

                if (rutaImagen != null && !rutaImagen.isEmpty()) {
                    ImageIcon icono = new ImageIcon(rutaImagen);
                    Image imgEscalada = icono.getImage().getScaledInstance(
                            labels[contador].getWidth(),
                            labels[contador].getHeight(),
                            Image.SCALE_SMOOTH
                    );
                    labels[contador].setIcon(new ImageIcon(imgEscalada));
                }

                nodoActual = nodoActual.getSiguiente();
                contador++;

                // Para evitar bucle infinito en lista circular
                if (nodoActual == inicio) {
                    break;
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Busqueda Binaria
     */
    /**
     * Realiza una búsqueda binaria recursiva en la lista ORDENADA.
     *
     * @param nombreBuscado Nombre de la imagen a buscar.
     * @return Nodo encontrado o null si no existe.
     */
    public Nodo busquedaBinariaRecursiva(String nombreBuscado) {
        if (listaVacia()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Convertimos temporalmente la lista circular en lineal para facilitar la búsqueda binaria
        inicio.setAnterior(null);
        fin.setSiguiente(null);

        Nodo resultado = busquedaBinariaRecursivaHelper(inicio, fin, nombreBuscado);

        // Restauramos la circularidad
        inicio.setAnterior(fin);
        fin.setSiguiente(inicio);

        return resultado;
    }

    /**
     * Helper recursivo para la búsqueda binaria.
     *
     * @param inicio Nodo inicial del subrango.
     * @param fin Nodo final del subrango.
     * @param nombreBuscado Nombre a buscar.
     * @return Nodo encontrado o null.
     */
    private Nodo busquedaBinariaRecursivaHelper(Nodo inicio, Nodo fin, String nombreBuscado) {
        // Caso base: el rango no es válido
        if (inicio == null || fin == null || inicio == fin.getSiguiente()) {
            return null;
        }

        // Obtenemos el nodo medio del rango actual
        Nodo medio = obtenerMitad(inicio, fin);

        // Comparamos el nombre del nodo medio con el buscado (ignorando mayúsculas)
        int comparacion = medio.getNombreImagen().compareToIgnoreCase(nombreBuscado);

        if (comparacion == 0) {
            return medio; // ¡Encontrado!
        } else if (comparacion > 0) {
            // Buscar en la mitad izquierda (antes del medio)
            return busquedaBinariaRecursivaHelper(inicio, medio.getAnterior(), nombreBuscado);
        } else {
            // Buscar en la mitad derecha (después del medio)
            return busquedaBinariaRecursivaHelper(medio.getSiguiente(), fin, nombreBuscado);
        }
    }

    /**
     * Obtiene el nodo medio entre dos nodos (inicio y fin).
     *
     * @param inicio Nodo inicial.
     * @param fin Nodo final.
     * @return Nodo medio.
     */
    private Nodo obtenerMitad(Nodo inicio, Nodo fin) {
        if (inicio == fin) {
            return inicio; // Solo hay un nodo en el rango
        }

        Nodo lento = inicio;
        Nodo rapido = inicio.getSiguiente();

        // Avanzamos 'rapido' dos pasos y 'lento' un paso hasta llegar al final
        while (rapido != fin && rapido != fin.getSiguiente()) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente();
            if (rapido != fin && rapido != fin.getSiguiente()) {
                rapido = rapido.getSiguiente();
            }
        }

        return lento;
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
        BotonLimpiarBuffer = new javax.swing.JButton();
        LNombrePNG = new javax.swing.JLabel();
        LDescPNG = new javax.swing.JLabel();
        TxtNombreImagen = new javax.swing.JTextField();
        LFecha = new javax.swing.JLabel();
        SpinnerFecha = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        TxtDescripcionImagen = new javax.swing.JTextField();
        LFecha1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        BotonCargarIma1 = new javax.swing.JButton();
        BotonBorrarImage1 = new javax.swing.JButton();
        BotonImportarImage1 = new javax.swing.JButton();
        BotonEmoji = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        BotonOrdenar = new javax.swing.JButton();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BotonAlbum = new javax.swing.JButton();
        BotonGaleria = new javax.swing.JButton();
        BotonConfi = new javax.swing.JButton();

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

        jSeparator2.setAlignmentX(1.0F);
        jSeparator2.setAlignmentY(1.0F);
        PanelBase.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 900, 20));

        TxtDescripcionImagen.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        PanelBase.add(TxtDescripcionImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 220, 30));

        LFecha1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LFecha1.setText("Colocar Fecha");
        PanelBase.add(LFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 120, -1));

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
        jPanel2.add(BotonCargarIma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, 50));

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
        jPanel2.add(BotonBorrarImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 220, 50));

        BotonImportarImage1.setBackground(new java.awt.Color(134, 188, 118));
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
        jPanel2.add(BotonImportarImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 220, 50));

        BotonEmoji.setBackground(new java.awt.Color(181, 181, 87));
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
        jPanel2.add(BotonEmoji, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 190, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel8.setText("Menu de Emojis: Click Izquierdo ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 160, -1));

        jButton3.setBackground(new java.awt.Color(139, 208, 220));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 200, 50));

        BotonOrdenar.setBackground(new java.awt.Color(201, 175, 231));
        BotonOrdenar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BotonOrdenar.setForeground(new java.awt.Color(255, 255, 255));
        BotonOrdenar.setText("Ordenar");
        BotonOrdenar.setBorderPainted(false);
        BotonOrdenar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarActionPerformed(evt);
            }
        });
        jPanel2.add(BotonOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 180, 50));

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

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoS.png"))); // NOI18N
        jLabel7.setText("Fotos");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 160, 50));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 218, 20));

        BotonAlbum.setBackground(new java.awt.Color(255, 102, 102));
        BotonAlbum.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonAlbum.setForeground(new java.awt.Color(255, 255, 255));
        BotonAlbum.setText("Album");
        BotonAlbum.setBorder(null);
        BotonAlbum.setBorderPainted(false);
        BotonAlbum.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAlbumActionPerformed(evt);
            }
        });
        jPanel3.add(BotonAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 230, 70));

        BotonGaleria.setBackground(new java.awt.Color(255, 102, 102));
        BotonGaleria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonGaleria.setForeground(new java.awt.Color(255, 255, 255));
        BotonGaleria.setText("Datos-Galeria");
        BotonGaleria.setBorder(null);
        BotonGaleria.setBorderPainted(false);
        BotonGaleria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonGaleria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGaleriaActionPerformed(evt);
            }
        });
        jPanel3.add(BotonGaleria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 70));

        BotonConfi.setBackground(new java.awt.Color(255, 102, 102));
        BotonConfi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BotonConfi.setForeground(new java.awt.Color(255, 255, 255));
        BotonConfi.setText("Creditos");
        BotonConfi.setBorderPainted(false);
        BotonConfi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonConfi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonConfiActionPerformed(evt);
            }
        });
        jPanel3.add(BotonConfi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 230, 80));

        PanelBase.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 720));

        getContentPane().add(PanelBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                JOptionPane.showMessageDialog(null, "Ingresa Nombre, Imagen y el Emoji", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                Insertar();
                actualizarImagen();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa Nombre, Imagen y el Emoji", "Aviso", JOptionPane.WARNING_MESSAGE);
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

        String link = "https://github.com/AngelSH10004/ProyectoIntegrador1";

        try {
            // Usar Desktop.getDesktop() para abrir el enlace en el navegador por defecto
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(link));
        } catch (IOException e) {
            // Si ocurre un error, lo imprimimos

        }
    }//GEN-LAST:event_BtnGuiaActionPerformed

    //Acciones de los Botones de la parte izquierda, Boton Confi
    private void BotonConfiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonConfiActionPerformed

        jTabbedPane1.setSelectedIndex(2); // Cambia al tercer panel

    }//GEN-LAST:event_BotonConfiActionPerformed

    //Acciones de los Botones de la parte izquierda, Boton Galeria
    private void BotonGaleriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGaleriaActionPerformed

        jTabbedPane1.setSelectedIndex(0); // Cambia al primer panel
    }//GEN-LAST:event_BotonGaleriaActionPerformed

    //Acciones de los Botones de la parte izquierda, Boton Album
    private void BotonAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAlbumActionPerformed

        jTabbedPane1.setSelectedIndex(1); // Cambia al segundo panel

    }//GEN-LAST:event_BotonAlbumActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        buscarBinario();
        actualizarImagen();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BotonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarActionPerformed

        if (listaVacia()) {
            JOptionPane.showMessageDialog(null, "Che amigo, la lista esta vacia, que vas a ordenar >:(", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            actualizarImagen(true);
            actualizarTexto();
        }
    }//GEN-LAST:event_BotonOrdenarActionPerformed

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

////////////////////////////////////////////////////////////////////
        //Este try es para implementar los temas para java del proyecto 
//        try {
////            UIManager.setLookAndFeel(new FlatLightLaf());
//            FlatCyanLightIJTheme.setup();
//        } catch (Exception ex) {
//            System.err.println("Failed to initialize LaF");
//        }
////////////////////////////////////////////////////////////////////
//        Aqui puedes modificar los diseños de los botones, textfield, etc...
//        UIManager.put("Button.arc", 999);
//        UIManager.put("Component.arc", 999);
//        UIManager.put("TextComponent.arc", 5);
////////////////////////////////////////////////////////////////////
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
    private javax.swing.JButton BotonOrdenar;
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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
