<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
 xmlns:wrs="http://java.sun.com/xml/ns/jdbc">
 
 <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes" />
 <xsl:template match="/">
  <xsl:element name="html">
   <xsl:element name="body">
    <xsl:element name="table">
     <xsl:attribute name="cellpadding">5</xsl:attribute>
     <xsl:attribute name="border">1</xsl:attribute>
     <xsl:element name="tr">
      <xsl:element name="th">Tree</xsl:element>
      <xsl:element name="th">Area</xsl:element>
     </xsl:element>
     <xsl:apply-templates select=".//wrs:currentRow"/>
    </xsl:element>
   </xsl:element>
  </xsl:element>
 </xsl:template>

 <xsl:template match="wrs:currentRow">
  <xsl:element name="tr">
   <xsl:element name="td">
    <xsl:value-of select="wrs:columnValue[1]"/>
   </xsl:element>
   <xsl:element name="td">
    <xsl:value-of select="wrs:columnValue[2]"/>
   </xsl:element>
  </xsl:element>
 </xsl:template>

</xsl:stylesheet>
