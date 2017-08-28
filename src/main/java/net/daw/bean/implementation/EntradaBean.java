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

public class EntradaBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private Date fecha_entrada;
    //@Expose
    //private Date hora_entrada;
    @Expose(serialize = false)
    private Integer id_usuario;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario;

    public EntradaBean() {
    }

    public EntradaBean(Integer id) {
        this.id = id;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    /*public Date getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Date hora_entrada) {
        this.hora_entrada = hora_entrada;
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
        strColumns += "fecha_entrada";
//        strColumns += "hora_entrada";
        strColumns += "id_usuario";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha_entrada) + ",";
        //       strColumns += EncodingUtilHelper.stringifyAndQuotate(hora_entrada) + ",";
        strColumns += id_usuario;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "fecha_entrada = " + EncodingUtilHelper.stringifyAndQuotate(fecha_entrada) + ",";
//        strPairs += "hora_entrada = " + EncodingUtilHelper.stringifyAndQuotate(hora_entrada) + ",";
        strPairs += "id_usuario = " + id_usuario;
        return strPairs;
    }

    @Override
    public EntradaBean fill(ResultSet oResultSet, Connection pooledConnection, UsuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
//        this.setdescripcion(oResultSet.getString("descripcion"));
        this.setFecha_entrada(oResultSet.getTimestamp("fecha_entrada"));
//        this.hora_entrada = oResultSet.getTimestamp("hora_entrada");
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
