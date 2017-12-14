import java.io.StringReader;
import java.io.StringWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.sql.rowset.WebRowSet;

import com.sun.rowset.WebRowSetImpl;

/**
 * Solution to Homework Assignment.
 * <p>
 * Copyright &#169; 2012 Ken Baclawski. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * <ol>
 * <li>Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 * <li>Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * </ol>
 * <p>
 * THIS SOFTWARE IS PROVIDED BY KEN BACLAWSKI ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL KEN BACLAWSKI OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * @author Ken Baclawski
 */
public class Trees {
    /** The database connection. */
    private Connection connection;
    /** The transformer factory. */
    private TransformerFactory factory = TransformerFactory.newInstance();

    /**
     * Connect to the database.
     * @param driver The fully-qualified class name of the driver.
     * @param server The connection string for the server.
     * @param user The username authorized to use the database.
     * @param password The password for gaining authorization.
     */
    public Trees(String driver, String server, String user, String password) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(server, user, password);
        } catch (Exception e) {
            System.out.println("Unable to connect to the database at " + server + " due to: " + e);
        }
    }

    /**
     * Disply the results of a query.
     * @param query The query that extracts the data from the database.
     * @param xslt The file containing the XSLT transform.
     * @param html The HTML file to be produced.
     * @throws Exception if the query or transformation fails.
     */
    public void displayAsHtml(String query, String xslt, String html) throws Exception {
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        WebRowSet wrs = new WebRowSetImpl();
        StringWriter writer = new StringWriter();
        wrs.writeXml(rs, writer);
        StringReader reader = new StringReader(writer.toString());
        StreamSource dataSource = new StreamSource(reader);
        StreamSource xsltSource = new StreamSource(new FileInputStream(xslt));
        Transformer transformer = factory.newTransformer(xsltSource);
        FileOutputStream htmlOutput = new FileOutputStream(html);
        transformer.transform(dataSource, new StreamResult(htmlOutput));
    }

    /**
     * Main program for the queries and transformations.
     * @param args The command-line arguments are not used.
     */
    public static void main(String[] args) throws Exception {
        Trees trees = new Trees("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/trees", "root", "abcd");
        String query1 = 
            "select t.scientificName, t.description, f.name, f.location " +
            "  from ForestTree ft join Forest f on ft.forest = f.id " +
	    "       right join TreeSpecies t on t.scientificName = ft.tree " +
            " where not exists(" +
            "         select * " +
            "           from ForestTree gt " +
            "          where gt.tree = ft.tree " +
            "            and gt.status <> 'extinct')";
        trees.displayAsHtml(query1, "query1.xsl", "result1.html");
	String query2 =
	    "select t.scientificName, sum(f.area) s " +
	    "  from TreeSpecies t, ForestTree ft, Forest f " +
	    " where ft.tree = t.scientificName " +
	    "   and ft.forest = f.id " +
	    "   and ft.status <> 'extinct' " +
	    "   and not exists(" +
	    "           select * " +
	    "             from ForestTree gt " +
	    "            where ft.tree = gt.tree " + 
	    "              and gt.status not in ('extinct', 'threatened')) " +
	    "group by t.scientificName " +
	    "order by s";
        trees.displayAsHtml(query2, "query2.xsl", "result2.html");
    }
}
