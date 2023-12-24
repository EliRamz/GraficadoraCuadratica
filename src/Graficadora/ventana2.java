package Graficadora;
/* Esta clase de JFrame, contiene las graficas tanto del plano cartesiano como de la funcion cuadratica, todo esto se grafica en su panel
 * en este estan los botones para aumentar o disminuir el tamaño de las graficas, las graficas se hacen tomando en cuenta los valores de 
 * la pantalla de ancho y alto, y los valore obtenidos de la clase ventana1, con esos valores se toman para realizar operaciones y conseguir
 * las coordenadas para graficas, estas graficas se hacen con la funcion drawLine, esta funcion necesita 4 coordenadas para graficar,
 * estos parametros de la clase ventana1, son tomados de clase transferecnia de datos, mediante el servicio de la clase objetoDeDatos,
 * recordemos que esta tiene un objeto para poder transferir datos entre los paneles, debido a que este objeto es estatico, se puede usar en 
 * cualquier parte del codgio donde se instancie un objeto de esta clase.
 * 
 *  las graficas se hacen tomando las proporciones de la pantalla y dividiendolas entre alto y ancho, en los parametros de la funcion drawLine
 *  esta funcin es la encargada de dibujar la lineas del plano cartesiano.
 *  
 *  para dibujar las lineas divisoras en el plano, se usan los ciclos for, estos ciclos de igual forma, usan drawLine y dividen el 
 *  plano en sus parametros,en los parametros se le suman ciertos valores para especificar el tamaño de las linas divisoras, 
 *  cabe destacar que estos estan medidos en pixeles.
 *  
 *  luego, se llama a el metodo para pintar la grafica, tambien se usa drawLine,se introducen las coordenadas de la funcion a graficar,
 *  estas son manejadas en otra clase, para manejar la escala cuando se trate de hacer zoom a la aplicacion y para mantener la funcion
 *  en el centrada, estos e hace sumandole la mitad del ancho y alto, y multiplicandole por la escala.
 *  
 *   el accionamiento de los botones, los botones de aumentar y disminuir , son para ampliar o reducir la grafica, funcionan de la misma 
 *   forma reduciendo o aumentando toda la escala que cambia la grafica, siempre que sucede esto, se borra la grafica anterior 
 *   y se dibuja la nueva sobre este panel. La nueva escala es establecida en un objeto modelo de la clase transferenciaDeDatos.
 *   
 *   cuando nos topamos con la clase accionBotones, se crea tambien un objeto donde se guarda el codgio del boton que fue presionado,este lo
 *   compara en condicionales, y se ejecuta la accion necesaria.
 *   
 *   para el boton volver, este solo te envia la panel anterios, dejando este atras.*/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ventana2 extends JFrame {
//campos
	private JPanel contentPane;
	private JButton aumentar;
	private JButton disminuir;
	private JButton anterior;
	private int ancho;
	private int alto;
	private double cons;
	private double x1mult;
	private double x2mult;
	private int escala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana2 frame = new ventana2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventana2() {
		// objeto de la clase objetos de datos
		transferenciaDeDatos modelo = objetoDeDatos.modeloDeTransferencia;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		ancho = getWidth();
		alto = getHeight();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBounds(646, 577, 113, 131);
		contentPane.add(panel);
		panel.setLayout(null);

		disminuir = new JButton("-");
		disminuir.setBounds(41, 63, 41, 23);
		panel.add(disminuir);
		disminuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotones(e);
			}
		});

		aumentar = new JButton("+");
		aumentar.setBounds(41, 29, 41, 23);
		panel.add(aumentar);

		anterior = new JButton("Volver");
		anterior.setBounds(14, 97, 89, 23);
		panel.add(anterior);
		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotones(e);
			}
		});
		aumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotones(e);
			}
		});

		// obtiene los datos de la clase transferencia de datos
		cons = modelo.getValorC();
		x1mult = modelo.getValorB();
		x2mult = modelo.getValorA();
		escala = modelo.getEscala();

	}

	public void paint(Graphics g) {
		super.paint(g);

		g.clearRect(0, 0, ancho, alto);// resetea el plano, lo deja en blanco
		g.setColor(Color.blue);// color del plano cartesiano

		// estos dibujan el plano , dividiendo la pantalla
		g.drawLine(ancho / 2, 0, ancho / 2, alto);
		g.drawLine(0, alto / 2, ancho, alto / 2);

		/*
		 * los ciclos for siguiente son para el dibujado de las lineas divisorias del
		 * plano cartesiano
		 */
		for (int x = 0; x < ancho; x += escala) {
			g.drawLine(x, (alto / 2) - 3, x, (alto / 2) + 3);
		}

		for (int y = 0; y < alto; y += escala) {
			g.drawLine((ancho / 2) - 3, y, (ancho / 2) + 3, (int) y);
		}

		paintGrafica(g);
	}

	// metodo para dibujar la grafica
	public void paintGrafica(Graphics g) {
		double x1 = -800;// parametros de dibujado para el ciclo for
		double x2 = 800;

		for (double i = -80; i < 80; i += 0.01) {
			g.fillOval((int) (((i) * escala + 20) + (ancho / 2)),
					(int) ((escala) * (-1 * (x2mult * i * i + x1mult * i + cons)) + (alto / 2)), 2, 2);
		}

	}

	public void accionBotones(ActionEvent e) {
		transferenciaDeDatos modelo = objetoDeDatos.modeloDeTransferencia;//// objeto de la clase objetos de datos
		Object boton = e.getSource();// objeto para tomar el evento presionado
		Graphics g = (Graphics) getGraphics();// objeto grafico para ser enviado por parametro

		if (boton.equals(aumentar)) {// accion para el boton donde se reduce la escala
			escala++;// aumento de escala
			modelo.setEscala(escala);// envio nueva escala
			paint(g);// llamo al metodo para pinta con la nueva escala

		} else if (boton.equals(disminuir)) {// accion para el boton donde se reduce la escala
			escala--;
			modelo.setEscala(escala);
			paint(g);

		} else if (boton.equals(anterior)) {// accion para el boton que hace volver al frame anterior
			new ventana1().setVisible(true);
			this.dispose();
		}

	}
}