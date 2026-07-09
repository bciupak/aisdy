
import csv
import os
import matplotlib.pyplot as plt
import matplotlib.cm as cm
CSV_PATH = os.path.join('ex4', 'performance_charts.csv')
OUT_DIR = os.path.join('ex4', 'charts')


def read_rows(path):
	if not os.path.exists(path):
		raise FileNotFoundError(path)
	rows = []
	with open(path, newline='') as f:
		r = csv.reader(f)
		header = next(r)
		for row in r:
			rows.append(row)
	return rows


def make_plots(rows):
	os.makedirs(OUT_DIR, exist_ok=True)

	groups = {}
	for sorter, arrange, size, time in rows:
		size = int(size)
		time = int(time)
		groups.setdefault(arrange, {}).setdefault(sorter, []).append((size, time))
	preferred = ['ASC', 'RAND', 'DESC']
	remaining = [a for a in groups.keys() if a not in preferred]
	order = [a for a in preferred if a in groups] + sorted(remaining)

	for arrange in order:
		sorters = groups[arrange]
		plt.figure()

		items = sorted(sorters.items(), key=lambda it: it[0])
		n_sorters = len(items)
	
		cmap = plt.get_cmap('tab20')
		if n_sorters > 1:
			colors = [cmap(i / max(1, n_sorters - 1)) for i in range(n_sorters)]
		else:
			colors = [cmap(0)]
		
		markers = ['o', 's', 'D', '^', 'v', '<', '>', 'p', 'h', 'x', '+', '*']
		for idx, (sorter, points) in enumerate(items):
			points.sort()
			xs = [p[0] for p in points]
			ys = [p[1] for p in points]
			plt.plot(xs, ys, marker=markers[idx % len(markers)], color=colors[idx], label=sorter)


		plt.title('Performance — ' + arrange)
		plt.xlabel('size')
		plt.ylabel('ns (median)')
		plt.yscale('log')
		plt.grid(True, which='both', linestyle='--', linewidth=0.5)
		ncol = max(1, min(6, len(sorters)))
		plt.legend(loc='upper center', bbox_to_anchor=(0.5, -0.18), ncol=ncol)
		fname = os.path.join(OUT_DIR, f'performance_{arrange}.png')
		plt.savefig(fname, bbox_inches='tight')
		print('Saved', fname)
		plt.close()


def main():
	try:
		rows = read_rows(CSV_PATH)
	except Exception as e:
		print('Error:', e)
		return
	make_plots(rows)


if __name__ == '__main__':
	main()



