package xyz.eliothmonroy.toppeliculas.model.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Clase que modela la respuesta recibida por el api de themoviesdb.org
 * Contiene un listado de películas
 */
public class Result {
    
    @SerializedName("page")
    private String page;
    
    @SerializedName("total_pages")
    private String total_pages;
    
    @SerializedName("results")
    private ArrayList<Movie> movies;
    
    @SerializedName("total_results")
    private String total_results;
    
    public String getPage() {
        return page;
    }
    
    public void setPage(String page) {
        this.page = page;
    }
    
    public String getTotal_pages() {
        return total_pages;
    }
    
    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }
    
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    
    public String getTotal_results() {
        return total_results;
    }
    
    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }
    
    @Override
    public String toString() {
        return "ClassPojo [page = " + page + ", total_pages = " + total_pages + ", movies = " + movies.toString() + ", total_results = " + total_results + "]";
    }
}