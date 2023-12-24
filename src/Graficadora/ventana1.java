package Graficadora;
/*Esta es una clase que extiende de JFrame , contiene un panel llamado contentPanel, este contiene campos de texto, un boton y etiquetas.
 * el boton es el encargado de tomar los datos de los campos de texto, estos datos son introducidos por el usuario.
 * Cuando el boton toma estos datos, los envia a la clase transferencia de datos donde son almacenados, esto mediante un objeto de esta clase 
 * que esta instanciado en la clase "objetoDeDatos", quien tiene un objeto instanciado en esta, justo en el metodo cambiar del boton que 
 * realiza estas funciones.
 * 
 *  En el boton al tomar el evento, envia los datos del evento al metodo cambiar, en el que se envia por parametro el evento, 
 *  aqui se instancia el obejto de la clase objeto de datos, este nuevo objeto se llamada modelo, el cual sivre para pasar 
 *  los datos de los textos de los campos de texto estos son convertidos de tipo String a Double, para poder ser enviadas a la clase.
 *  
 *  contiene un majejo de errores, para los datos introducidos que no son numericos o para datos nulos, tambien tiene condicionales 
 *  que muestran si la funcion es imaginaria o no, dependiendo de los parametros de la funcion imaginaria.
 * 
 * si hay un error, la variable error cambia a verdadero para que el codigo se dentenga, si no continua y se ejecuta el siguiente panel donde 
 * esta la grafica, y este frame llamado ventana1 desaparece.*/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ventana1 extends JFrame {
//campos privados para obtener los datos
	private JPanel contentPane;
	private JTextField datoA;
	private JTextField datoB;
	private JTextField datoC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana1 frame = new ventana1();
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
	public ventana1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton grafica = new JButton("Graficar");
		grafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiar();
			}
		});

		grafica.setBounds(5, 333, 374, 23);
		contentPane.add(grafica);

		datoA = new JTextField();
		datoA.setBounds(95, 75, 86, 20);
		contentPane.add(datoA);
		datoA.setColumns(10);

		datoB = new JTextField();
		datoB.setBounds(95, 147, 86, 20);
		contentPane.add(datoB);
		datoB.setColumns(10);

		datoC = new JTextField();
		datoC.setColumns(10);
		datoC.setBounds(95, 217, 86, 20);
		contentPane.add(datoC);

		JLabel labelA = new JLabel("A =");
		labelA.setBounds(60, 78, 25, 14);
		contentPane.add(labelA);

		JLabel labelB = new JLabel("B =");
		labelB.setBounds(60, 150, 25, 14);
		contentPane.add(labelB);

		JLabel labelC = new JLabel("C =");
		labelC.setBounds(60, 220, 25, 14);
		contentPane.add(labelC);
	}

	// Luego de ingresado los datos, son procesados y se cambia a la ventana 2
	public void cambiar() {
		// objeto de la clase objetos de datos
		transferenciaDeDatos modelo = objetoDeDatos.modeloDeTransferencia;
		boolean error = false;// variable para saber cuando se ejecuto un error o no

		try {

			// estos condicionales, son para indicar cuanfdo una funcion es imaginaria
			if ((Double.parseDouble(datoA.getText()) > 0 && (Double.parseDouble(datoB.getText()) == 0)
					&& (Double.parseDouble(datoC.getText()) > 0))) {
				JOptionPane.showMessageDialog(null, "Esta es una funcion Imaginaria");
			}
			if ((Double.parseDouble(datoA.getText()) < 0 && (Double.parseDouble(datoB.getText()) == 0)
					&& (Double.parseDouble(datoC.getText()) < 0))) {
				JOptionPane.showMessageDialog(null, "Esta es una funcion Imaginaria");
			}
			// se establecen los valores tomados de los texto y se establecen a la clase
			// transferencia de datos, estos son tomados luegos
			modelo.setValorA(Double.parseDouble(datoA.getText()));
			modelo.setValorB(Double.parseDouble(datoB.getText()));
			modelo.setValorC(Double.parseDouble(datoC.getText()));

			// el error ocurrido es datos no numericos
		} catch (NumberFormatException e) {
			error = true;// hubo un error, entonces se cambia la variable
			JOptionPane.showMessageDialog(null, "No se ingreso un valor numerico ", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (error == false) {// si no hay error, se ejecuta la siguiente linea de codigo
			new ventana2().setVisible(true);
			// esto esconde esta ventana1
			this.dispose();

		}
	}
}