

#ifdef STANDARD
#include <string.h>
#ifdef __WIN__
typedef unsigned __int64 ulonglong;
typedef __int64 longlong;
#else
typedef unsigned long long ulonglong;
typedef long long longlong;
#endif /*__WIN__*/
#else
#include <my_global.h>
#include <my_sys.h>
#endif
#include <mysql.h>
#include <m_ctype.h>
#include <m_string.h>

#ifdef HAVE_DLOPEN

extern "C" {
	my_bool		levenshtein_init(UDF_INIT *initid, UDF_ARGS *args,
	                             char *message);
	void			levenshtein_deinit(UDF_INIT *initid);
	longlong		levenshtein(UDF_INIT *initid, UDF_ARGS *args,
	                        char *is_null, char *error);
}


my_bool levenshtein_init(UDF_INIT *initid, UDF_ARGS *args, char *message)
{
	int *workspace;

	/* check for 2 rguments */
	if (args->arg_count != 2)
	{
		strcpy(message, "LEVENSHTEIN() requires two arguments");
		return 1;
	}
	/*check if 2 arguments are strings */
	else if (args->arg_type[0] != STRING_RESULT || 
	         args->arg_type[1] != STRING_RESULT)
	{
		strcpy(message, "LEVENSHTEIN() requires two string arguments");
		return 1;
	}

	initid->max_length = 3;

	initid->maybe_null = 0;

	
   workspace = new int[(args->lengths[0] + 1) * (args->lengths[1] + 1)];
		
	if (workspace == NULL)
	{
		strcpy(message, "Failed to allocate memory for levenshtein function");
		return 1;
	}

	
	initid->ptr = (char*) workspace;

	return 0;
}

void levenshtein_deinit(UDF_INIT *initid)
{
	if (initid->ptr != NULL)
	{
		delete [] initid->ptr;
	}
}


longlong levenshtein(UDF_INIT *initid, UDF_ARGS *args, char *is_null,
                     char *error)
{
	
  	const char *s = args->args[0];
  	const char *t = args->args[1];
	int *d = (int*) initid->ptr;

	longlong n, m;
  	int b,c,f,g,h,i,j,k,min;

	

  	n = (s == NULL) ? 0 : args->lengths[0];
  	m = (t == NULL) ? 0 : args->lengths[1];

  	if(n != 0 && m != 0)
  	{
		
		n++;
		m++;

		/* initialize first row to 0..n */
    	for (k = 0; k < n; k++)
		{
			d[k] = k;
		}

		/* initialize first column to 0..m */
    	for (k = 0; k < m; k++)
		{
      	d[k * n] = k;
		}

		g = 0;
    	for (i = 1; i < n; i++)
		{
			
			k = i;

			/* throughout the for j loop, f will equal j minus one */
			f = 0;
      	for (j = 1; j < m; j++)
			{
				
				h = k;
				/* k = (j * n + i) */
				k += n;

				
				min = d[h] + 1;
				b = d[k-1] + 1;
				c = d[h-1] + (s[g] == t[f] ? 0 : 1);

				if (b < min) { min = b; }
				if (c < min) { min = c; }

				d[k] = min;

				f = j;
      	}

			g = i;
		}

		
    	return (longlong) d[k];
  	}
	else if (n == 0)
	{
		return m;
	}
	else
	{
		return n;
	}
}

#endif /* HAVE_DLOPEN */
