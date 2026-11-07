package com.roonin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.repository.catalogs.ConctbRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilDate.
 */
public class UtilDate {

	/** The Constant DAY. */
	private static final int DAY = 1;

	/** The Constant FORMATE_DATE. */
	private static final String FORMATE_DATE = "dd/MM/YYYY";

	/** The Constant MONTHS_NAMES. */
	private static final String[] MONTHS_NAMES = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO",
			"AGOSTO", "SEPTIEMRE", "OCTUBRE", "NOVIEMRBE", "DICIEMBRE" };

	/** The conctb repository. */
	@Autowired
	private static ConctbRepository conctbRepository;

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public static int getDate() {
		return getCalendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public static int getMonth() {
		return getCalendar().get(Calendar.MONTH) + 1;
	}

	/**
	 * Gets the calendar.
	 *
	 * @return the calendar
	 */
	public static Calendar getCalendar() {
		Calendar calendar = GregorianCalendar.getInstance();
		return calendar;
	}

	/**
	 * Gets the date of system.
	 *
	 * @return the date of system
	 */
	public static String getDateOfSystem() {
		Date date = new Date();
		String dateSystem = new SimpleDateFormat(FORMATE_DATE).format(date);
		return dateSystem;
	}

	public static String getFormatDate(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(FORMATE_DATE);
		Date f = new Date(fecha);
		return formatoDelTexto.format(f);
	}

	/**
	 * Gets the last day.
	 *
	 * @param month the month
	 * @return the last day
	 */
	public static int getLastDay(int month) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(getYear(), (month - 1), DAY);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Gets the last day of month.
	 *
	 * @param date the date
	 * @return the last day of month
	 */
	public static int getLastDayOfMonth(Date date) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);

		return calendario.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public static int getYear() {
		return getCalendar().get(Calendar.YEAR);
	}

	/**
	 * Gets the month name.
	 *
	 * @param month the month
	 * @return the month name
	 */
	public static String getMonthName(int month) {
		if (month < 0 || month > 11)
			return "";
		return MONTHS_NAMES[(month - 1)];
	}

	/**
	 * Gets the formated date string.
	 *
	 * @param format the format
	 * @param date   the date
	 * @return the formated date string
	 */
	public static String getFormatedDateString(String format, Date date) {
		if (date == null)
			return "";
		if (format == null)
			return date.toString();
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Convert string to date.
	 *
	 * @param format the format
	 * @param date   the date
	 * @return the date
	 */
	public static Date convertStringToDate(String format, String date) {
		if (date == null || format == null)
			return null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatDate(String formato, String fecha) {

		Date d = new Date(fecha);
		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(d).toString();

	}

	public static String converStringToDate(String formato, String fecha) {

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");

		Date f = null;
		try {

			f = formatoDelTexto.parse(fecha);

		} catch (ParseException ex) {

			ex.printStackTrace();

		}
		return f.toString();

	}

	/**
	 * Gets the ano emp.
	 *
	 * @param idSector the id sector
	 * @return the ano emp
	 */
	public static Integer getAnoEmp(Integer idSector) {
		return conctbRepository.findByIdsector(idSector).getAnoemp();
	}

	/**
	 * Gets the last day by ano emp.
	 *
	 * @param month the month
	 * @param anio  the anio
	 * @return the last day by ano emp
	 */
	public static int getLastDayByAnoEmp(int month, int anio) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, (month - 1), DAY);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date getDateSystem() {
		return Calendar.getInstance().getTime();
	}

}
