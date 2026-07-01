package com.itsqmet.codehub.service;

import com.itsqmet.codehub.model.Proyecto;
import com.itsqmet.codehub.repository.ProyectoRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProyectoReporteService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public byte[] generarReporteProyectosPdf() {
        try {
            List<Proyecto> proyectos = proyectoRepository.findAll();

            ByteArrayOutputStream salida = new ByteArrayOutputStream();

            Document documento = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(documento, salida);

            documento.open();

            Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font textoFont = new Font(Font.HELVETICA, 11);
            Font encabezadoFont = new Font(Font.HELVETICA, 10, Font.BOLD);

            Paragraph titulo = new Paragraph("Reporte de Proyectos", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            documento.add(new Paragraph("Fecha de generación: " + LocalDateTime.now(), textoFont));
            documento.add(new Paragraph("Total de proyectos registrados: " + proyectos.size(), textoFont));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);

            tabla.addCell(new Paragraph("ID", encabezadoFont));
            tabla.addCell(new Paragraph("Nombre", encabezadoFont));
            tabla.addCell(new Paragraph("Estado", encabezadoFont));
            tabla.addCell(new Paragraph("Fecha inicio", encabezadoFont));
            tabla.addCell(new Paragraph("Presupuesto", encabezadoFont));
            tabla.addCell(new Paragraph("Cliente", encabezadoFont));
            tabla.addCell(new Paragraph("Empleados", encabezadoFont));

            for (Proyecto proyecto : proyectos) {
                tabla.addCell(String.valueOf(proyecto.getId()));
                tabla.addCell(proyecto.getNombre());
                tabla.addCell(proyecto.getEstado());
                tabla.addCell(proyecto.getFechaInicio());
                tabla.addCell("$ " + proyecto.getPresupuesto());

                String cliente = proyecto.getCliente() != null
                        ? "Cliente ID: " + proyecto.getCliente().getId()
                        : "Sin cliente";

                String empleados = proyecto.getEmpleados() != null
                        ? String.valueOf(proyecto.getEmpleados().size())
                        : "0";

                tabla.addCell(cliente);
                tabla.addCell(empleados);
            }

            documento.add(tabla);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Reporte generado por el sistema CodeHub.", textoFont));

            documento.close();

            return salida.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte PDF de proyectos", e);
        }
    }
}