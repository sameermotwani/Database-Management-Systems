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
      <xsl:element name="th">Forest</xsl:element>
      <xsl:element name="th">Location</xsl:element>
     </xsl:element>
     <xsl:for-each select="//wrs:currentRow">
      <xsl:variable name="theCurrentTree">
       <xsl:value-of select="wrs:columnValue[1]"/>
      </xsl:variable>
      <xsl:element name="tr">
       <xsl:if test="not(preceding-sibling::wrs:currentRow/wrs:columnValue[1]=$theCurrentTree)">
        <xsl:element name="td">
         <xsl:attribute name="rowspan">
          <xsl:value-of select="count(//wrs:currentRow[wrs:columnValue[1]=$theCurrentTree])"/>
         </xsl:attribute>
         <xsl:value-of select="$theCurrentTree"/>
        </xsl:element>
       </xsl:if>
       <xsl:element name="td">
        <xsl:value-of select="wrs:columnValue[3]"/>
       </xsl:element>
       <xsl:element name="td">
        <xsl:value-of select="wrs:columnValue[4]"/>
       </xsl:element>
      </xsl:element>
     </xsl:for-each>
    </xsl:element>
    <xsl:element name="table">
     <xsl:attribute name="cellpadding">5</xsl:attribute>
     <xsl:attribute name="border">1</xsl:attribute>
     <xsl:element name="tr">
      <xsl:element name="th">Tree</xsl:element>
      <xsl:element name="th">Description</xsl:element>
     </xsl:element>
     <xsl:apply-templates select=".//wrs:currentRow"/>
    </xsl:element>
   </xsl:element>
  </xsl:element>
 </xsl:template>

 <xsl:template match="wrs:currentRow">
  <xsl:variable name="theCurrentTree">
   <xsl:value-of select="wrs:columnValue[1]"/>
  </xsl:variable>
  <xsl:if test="not(preceding-sibling::wrs:currentRow/wrs:columnValue[1]=$theCurrentTree)">
   <xsl:element name="tr">
    <xsl:element name="td">
     <xsl:value-of select="wrs:columnValue[1]"/>
    </xsl:element>
    <xsl:element name="td">
     <xsl:value-of select="wrs:columnValue[2]"/>
    </xsl:element>
   </xsl:element>
  </xsl:if>
 </xsl:template>

</xsl:stylesheet>
