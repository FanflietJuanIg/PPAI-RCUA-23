package org.example.ppaiprueba.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
public class Repository<T> {

        private final EntityManager em;
        private final Class<T> clase;

        public Repository(Class<T> clase) {
            em = DbContext.getInstance().getManager();
            this.clase = clase;
        }

        public void guardar(T entidad) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(entidad);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }

        public T actualizar(T entidad) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                T entidadActualizada = em.merge(entidad);
                tx.commit();
                return entidadActualizada;
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }

        public T buscarPorId(Object id) {
            return em.find(clase, id);
        }

        public void eliminar(T entidad) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                entidad = em.merge(entidad); // asegura que esté en contexto
                em.remove(entidad);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }

        public List<T> listarTodos() {
            return em.createQuery("SELECT e FROM " + clase.getSimpleName() + " e", clase)
                    .getResultList();
        }
}
