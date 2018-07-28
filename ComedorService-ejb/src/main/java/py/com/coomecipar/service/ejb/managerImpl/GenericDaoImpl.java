package py.com.coomecipar.service.ejb.managerImpl;

import py.com.coomecipar.service.ejb.manager.GenericDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManager;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.hibernate.HibernateMetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import java.util.ListIterator;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class GenericDaoImpl<T, ID extends Serializable> implements
        GenericDao<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

    protected JPASearchProcessor processor;

    @PersistenceContext
    private EntityManager em;

    public GenericDaoImpl() {
    }

    protected abstract Class<T> getEntityBeanType();

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {

        if (em == null) {
            throw new IllegalStateException("EntityManager no esta seteado");
        }

        return em;
    }

    protected SessionFactory getSessionFactory() {

        if (this.em.getDelegate() instanceof HibernateEntityManager) {
            return ((HibernateEntityManager) this.getEm().getDelegate())
                    .getSession().getSessionFactory();
        } else {
            return ((Session) this.getEm().getDelegate()).getSessionFactory();
        }

    }

    private Session getSession() {

        if (this.em.getDelegate() instanceof HibernateEntityManager) {
            return ((HibernateEntityManager) this.getEm().getDelegate())
                    .getSession();
        } else {
            return ((Session) this.getEm().getDelegate());
        }

    }

    @Override
    public T getIdHandler(ID id) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(getEntityBeanType().getSimpleName()).append(" o ");
        queryString.append("where o.").append("idhandler").append(" = ").append(id);

        LOGGER.info(queryString.toString());

        final Query query = this.em.createQuery(queryString.toString());

        return (T) query.getSingleResult();
    }

    @Override
    public int total(T ejemplo) {
        return list(ejemplo, true).size();
    }

    @Override
    public int total(T ejemplo, boolean like) {
        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));

        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, null, true,
                null, null, null,
                null, like, true, null, null, null, null, null,
                null, null, null, null, null, true, null, null);

        return jpaSP.count(em, searchConfig);
    }

    @Override
    public int total(T ejemplo, String[] atributos,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual,
            String campoComparacion, List<Object> valoresComparacion, String tipoFiltro, String[] camposNotNull, String tipoFiltroNull) {
        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));

        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, atributos, true,
                null, null, null,
                null, true, true, null, null, campoComparacion, valoresComparacion, tipoFiltro,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, null, true, camposNotNull, tipoFiltroNull);

        return jpaSP.count(em, searchConfig);
    }

    @Override
    public int total(T ejemplo, String[] atributos,
            String[] orderByAttrList, String[] orderByDirList,
            String camposDistintos, boolean distintos,
            String[] camposNotNull, String tipoFiltroNull) {
        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));

        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, null, true,
                null, null, orderByAttrList,
                orderByDirList, true, true, null, null, null, null, null,
                null, null, null, null, camposDistintos, distintos, camposNotNull, tipoFiltroNull);

        return jpaSP.count(em, searchConfig);
    }

    @Override
    public T get(ID id) {
        return (T) getEm().find(getEntityBeanType(), id);
    }

    @Override
    public Map<String, Object> getLike(T ejemplo, String[] atributos) {

        List<Map<String, Object>> lista = this.listAtributos(ejemplo,
                atributos);

        if (lista.isEmpty()) {
            return null;
        } else if (lista.size() == 1) {
            return lista.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public T get(T ejemplo) {
        List<T> list = this.list(ejemplo, false, 0, 1, null,
                null, false,
                false, null, null, null,
                null, null, null, null, null, false, null, null);

        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public Map<String, Object> getAtributos(T ejemplo, String[] atributos) {
        List<Map<String, Object>> lista = this.listAtributos(ejemplo,
                atributos);

        if (lista.isEmpty()) {
            return null;
        }

        if (lista.size() == 1) {
            return lista.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public Map<String, Object> getAtributos(T ejemplo, String[] atributos, boolean like, boolean caseSensitive) {
        List<Map<String, Object>> lista = this.listAtributos(ejemplo, atributos, true, 0, 2,
                null, null, like, caseSensitive, null, null, null, null, null, null, null, null, null, null, false, null, null);

        if (lista.isEmpty()) {
            return null;
        }

        if (lista.size() == 1) {
            return lista.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public void save(T entity) throws Exception {
        this.getEm().persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        this.getSession().update(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        T entity = this.get(id);

        if (entity != null) {
            this.getEm().remove(entity);
        }

    }

    @Override
    public void delete(T entity) throws Exception {
        if (entity != null) {
            this.getEm().remove(entity);
        }

    }

    @Override
    public List<T> list() {
        return this.list(null, true, null, null, null, null, false,
                false, null, null, null,
                null, null, null, null, null, false, null, null);
    }

    @Override
    public List<T> list(Integer primerResultado, Integer cantResultado) {
        return this.list(null, false, primerResultado, cantResultado, null,
                null, false,
                false, null, null, null,
                null, null, null, null, null, false, null, null);
    }

    @Override
    public List<T> list(T ejemplo) {
        return this.list(ejemplo, true);
    }

    @Override
    public List<T> list(T ejemplo, boolean like) {
        return this.list(ejemplo, true, null, null, null, null, like, false,
                null, null, null,
                null, null, null, null,
                null, false, null, null);
    }

    @Override
    public List<T> list(T ejemplo, String orderByAttrList, String orderByDirList) {
        return this.list(ejemplo, true, null, null,
                new String[]{orderByAttrList},
                new String[]{orderByDirList}, false,
                false, null, null, null,
                null, null, null, null, null, false, null, null);
    }
    
    @Override
    public List<T> list(T ejemplo, String orderByAttrList, String orderByDirList, String[] camposNotNull, String tipoFiltroNull) {
        return this.list(ejemplo, true, null, null,
                new String[]{orderByAttrList},
                new String[]{orderByDirList}, false,
                false, null, null, null,
                null, null, null, null, null, false, camposNotNull, tipoFiltroNull);
    }

    @Override
    public List<T> list(T ejemplo, String orderByAttrList,
            String orderByDirList, boolean like) {
        return this.list(ejemplo, true, null, null,
                new String[]{orderByAttrList},
                new String[]{orderByDirList}, like,
                false, null, null, null,
                null, null, null, null, null, false, null, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> list(T ejemplo, boolean all, Integer primerResultado,
            Integer cantResultados, String[] orderByAttrList,
            String[] orderByDirList, boolean like, boolean caseSensitive, String campoComparacion, List<Object> valoresComparacion, String tipoFiltro,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual,
            String camposDistintos, boolean distintos, String[] camposNotNull, String tipoFiltroNull) {

        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));

        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, null, all,
                primerResultado, cantResultados, orderByAttrList,
                orderByDirList, like, caseSensitive, null, null, campoComparacion, valoresComparacion, tipoFiltro,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, camposDistintos, distintos, camposNotNull, tipoFiltroNull);

        return jpaSP.search(em, searchConfig);
    }

    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo, String[] atributos) {
        return this.listAtributos(ejemplo, atributos, true);
    }

    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo, String[] atributos, String[] camposNotNull, String tipoFiltroNull) {
        return this.listAtributos(ejemplo, atributos, true, 0, null,
                null, null, true, true, null, null, null, null, null, null, null, null, null, null, false, camposNotNull, tipoFiltroNull);
    }

    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo,
            String[] atributos, boolean like) {
        return this.listAtributos(ejemplo, atributos, true, 0, null,
                null, null, like, true, null, null, null, null, null, null, null, null, null, null, false, null, null);
    }

    public List<Map<String, Object>> listAtributos(T ejemplo, String[] atributos,
            boolean all, Integer primerResultado, Integer cantResultados,
            String[] orderByAttrList, String[] orderByDirList, boolean like, boolean caseSensitive,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual) {

        return this.listAtributos(ejemplo, atributos, all, primerResultado, cantResultados,
                orderByAttrList, orderByDirList, like, caseSensitive,
                null, null, null, null, null,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, null, false, null, null);

    }
    
    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo,
		String[] atributos, boolean all, Integer primerResultado,
		Integer cantResultados, String[] orderByAttrList,
		String[] orderByDirList, boolean like, boolean caseSensitive,
		String propiedadFiltroComunes, String comun, String campoComparacion,List<Object> valoresComparacion, String tipoFiltro,
		List<String> atrMayIgual, List<Object> objMayIgual,List<String> atrMenIgual, List<Object> objMenIgual,
		String camposDinamicos, boolean distintos) {
        
        return this.listAtributos(ejemplo, atributos, all, primerResultado, cantResultados,
                orderByAttrList, orderByDirList, like, caseSensitive,
                propiedadFiltroComunes, comun, campoComparacion, valoresComparacion, tipoFiltro,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, camposDinamicos, distintos, null, null);
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo,
            String[] atributos, boolean all, Integer primerResultado,
            Integer cantResultados, String[] orderByAttrList,
            String[] orderByDirList, boolean like, boolean caseSensitive,
            String propiedadFiltroComunes, String comun, String campoComparacion, List<Object> valoresComparacion, String tipoFiltro,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual,
            String camposDistintos, boolean distintos, String[] camposNotNull, String tipoFiltroNull) {

        if (atributos == null || atributos.length == 0) {
            throw new RuntimeException(
                    "La lista de propiedades no puede ser nula o vacía");
        }

        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));

        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, atributos,
                all, primerResultado, cantResultados, orderByAttrList,
                orderByDirList, like, caseSensitive,
                propiedadFiltroComunes, comun, campoComparacion, valoresComparacion, tipoFiltro,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, camposDistintos, distintos, camposNotNull, tipoFiltroNull);

        return jpaSP.search(em, searchConfig);
    }

    private Search getSearchConfig(JPASearchProcessor jpaSP, T ejemplo,
            String[] atributos, boolean all, Integer primerResultado,
            Integer cantResultados, String[] orderByAttrList,
            String[] orderByDirList, boolean like, boolean caseSensitive,
            String propiedadFiltroComunes, String valorComun,
            String campoComparacion, List<Object> valoresComparacion, String tipoFiltro,
            List<String> atrMayIgual, List<Object> objMayIgual,
            List<String> atrMenIgual, List<Object> objMenIgual,
            String camposDistintos, boolean distintos, String[] camposNotNull, String tipoFiltroNull) {

        Search searchConfig = new Search(this.getEntityBeanType());

        if (ejemplo != null) {
            ExampleOptions exampleOptions = new ExampleOptions();
            exampleOptions.setExcludeNulls(true);

            if (like) {
                exampleOptions.setLikeMode(ExampleOptions.ANYWHERE);
            }

            exampleOptions.setIgnoreCase(caseSensitive);

            searchConfig.addFilter(jpaSP.getFilterFromExample(ejemplo,
                    exampleOptions));
        }

        if (atrMayIgual != null && objMayIgual != null && atrMayIgual.size() == objMayIgual.size()) {
            int index = 0;
            for (String atr : atrMayIgual) {
                if (objMayIgual.get(index) != null) {
                    Filter[] filtros = new Filter[2];
                    filtros[0] = Filter.isNull(atr);
                    filtros[1] = Filter.greaterOrEqual(atr, objMayIgual.get(index));
                    searchConfig.addFilterOr(filtros);
                }
                index++;
            }
        }

        if (atrMenIgual != null && objMenIgual != null && atrMenIgual.size() == objMenIgual.size()) {
            int index = 0;

            for (String atr : atrMenIgual) {
                if (objMenIgual.get(index) != null) {
                    Filter[] filtros = new Filter[2];
                    filtros[0] = Filter.isNull(atr);
                    filtros[1] = Filter.lessOrEqual(atr, objMenIgual.get(index));
                    searchConfig.addFilterOr(filtros);
                }
                index++;
            }
        }

        if (valoresComparacion != null && campoComparacion != null && tipoFiltro != null) {
            int tam = valoresComparacion.size() / 1000;
            tam++;

            int inicio = 0;

            Filter[] filtros = new Filter[tam];
            for (int i = 0; i < tam; i++) {
                if (tipoFiltro.compareTo("OP_IN") == 0) {
                    filtros[i] = Filter.in(campoComparacion, valoresComparacion.subList(inicio, Math.min(valoresComparacion.size(), inicio + 1000)));
                } else {
                    filtros[i] = Filter.notIn(campoComparacion, valoresComparacion.subList(inicio, Math.min(valoresComparacion.size(), inicio + 1000)));
                }
                inicio += 1000;
            }
            if (tipoFiltro.compareTo("OP_IN") == 0) {
                searchConfig.addFilterOr(filtros);
            } else {
                searchConfig.addFilterAnd(filtros);
            }
        }

        if (tipoFiltroNull != null && camposNotNull != null) {

            Filter[] filtros = new Filter[camposNotNull.length];

            for (int j = 0; j < camposNotNull.length; j++) {
                if (tipoFiltroNull.compareTo("NOT_NULL") == 0) {
                    filtros[j] = Filter.isNotNull(camposNotNull[j]);
                } else {
                    filtros[j] = Filter.isNull(camposNotNull[j]);
                }
            }

            searchConfig.addFilterAnd(filtros);
        }

        if (valorComun != null && propiedadFiltroComunes != null) {

            String[] lista = propiedadFiltroComunes.split(",");
            String[] valores = valorComun.split(" ");

            for (int j = 0; j < valores.length; j++) {
                int index = 0;
                Filter[] filtros = new Filter[lista.length];

                for (int i = 0; i < lista.length; i++) {
                    Filter f = new Filter();
                    f.setProperty(lista[i]);
                    f.setValue("%" + valores[j] + "%");
                    f.setOperator(Filter.OP_ILIKE);
                    filtros[index] = f;
                    index++;
                }
                searchConfig.addFilterOr(filtros);
            }

        }

        if (!all && primerResultado != null && cantResultados != null) {
            searchConfig.setFirstResult(primerResultado);
            searchConfig.setMaxResults(cantResultados);
        }

        if (atributos != null && atributos.length > 0) {
            for (String a : atributos) {
                searchConfig.addField(a);
            }
            searchConfig.setResultMode(Search.RESULT_MAP);
        }

        if (distintos && camposDistintos != null) {
            String[] lista = camposDistintos.split(",");
            searchConfig.setDistinct(distintos);
            for (String atributo : lista) {
                searchConfig.addField(atributo);
            }

        } else {

            if (orderByAttrList != null && orderByDirList != null
                    && orderByAttrList.length == orderByDirList.length) {
                for (int i = 0; i < orderByAttrList.length; i++) {
                    if (orderByDirList[i].equalsIgnoreCase("desc")) {
                        searchConfig.addSortDesc(orderByAttrList[i]);
                    } else {
                        searchConfig.addSortAsc(orderByAttrList[i]);
                    }
                }
            } else if ((orderByAttrList != null && orderByDirList == null)
                    || (orderByAttrList == null && orderByDirList != null)) {
                throw new RuntimeException("No puede proporcionarse una lista de "
                        + "atributos para ordenamiento sin la correspondiente "
                        + "lista de direcciones de ordenamiento, o viceversa");
            } else if (orderByAttrList != null && orderByDirList != null
                    && orderByAttrList.length != orderByDirList.length) {
                throw new RuntimeException("No puede proporcionarse una lista de "
                        + "atributos para ordenamiento de tamaño dieferente a la "
                        + "lista de direcciones de ordenamiento");
            }

        }
        return searchConfig;
    }

}
