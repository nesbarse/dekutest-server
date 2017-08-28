/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 *
 * sisane-server: Helps you to develop easily AJAX web applications
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/sisane-server
 *
 * sisane-server is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.helper.statics.EncodingUtilHelper;
import net.daw.dao.implementation.UsuarioDao;

public class SalidaBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private Date fecha_salida;
    //@Expose
    //private Date hora_salida;
    @Expose(serialize = false)
    private Integer id_usuario;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario;

    public SalidaBean() {
    }

    public SalidaBean(Integer id) {
        this.id = id;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    /*public Date getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Date hora_salida) {
        this.hora_salida = hora_salida;
    }*/
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fecha_salida";
//        strColumns += "hora_salida";
        strColumns += "id_usuario";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha_salida) + ",";
        //       strColumns += EncodingUtilHelper.stringifyAndQuotate(hora_salida) + ",";
        strColumns += id_usuario;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "fecha_salida = " + EncodingUtilHelper.stringifyAndQuotate(fecha_salida) + ",";
//        strPairs += "hora_salida = " + EncodingUtilHelper.stringifyAndQuotate(hora_salida) + ",";
        strPairs += "id_usuario = " + id_usuario;
        return strPairs;
    }

    @Override
    public SalidaBean fill(ResultSet oResultSet, Connection pooledConnection, UsuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
//        this.setdescripcion(oResultSet.getString("descripcion"));
        this.setFecha_salida(oResultSet.getTimestamp("fecha_salida"));
//        this.hora_salida = oResultSet.getTimestamp("hora_salida");
        this.id_usuario = oResultSet.getInt("id_usuario");
        if (expand > 0) {
            UsuarioDao eDao = new UsuarioDao(pooledConnection, oPusuarioBean_security, null);
            UsuarioBean eBean = new UsuarioBean();
            eBean.setId(this.id_usuario);
            this.obj_usuario = eDao.get(eBean, expand - 1);
//            UsuarioDao tDao = new UsuarioDao(pooledConnection, oPusuarioBean_security, null);
//            UsuarioBean tBean = new UsuarioBean();
//            tBean.setId(this.id_usuario);
//            this.obj_usuario = tDao.get(tBean, expand - 1);
        }
        return this;
    }

}
