/*
 * Copyright (C) 2010-2014 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler; // VanillaIRC
import com.minecraftonline.vanillairc.VanillaIRC; // VanillaIRC
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class lz {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lh> i = Lists.newArrayList();
    private final Map<UUID, lh> j = Maps.newHashMap();
    private final me k;
    private final lw l;
    private final ma m;
    private final mg n;
    private final Map<UUID, mx> o;
    private avy p;
    private boolean q;
    protected int e;
    private int r;
    private aer.a s; // VanillaIRC -- faulty decompilation: aer -> aer.a
    private boolean t;
    private int u;

    public lz(MinecraftServer var1) {
        this.k = new me(a);
        this.l = new lw(b);
        this.m = new ma(c);
        this.n = new mg(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(ek var1, lh var2) {
        GameProfile var3 = var2.cf();
        lv var4 = this.h.aF();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dn var7 = this.a(var2);

        var2.a((aeo) this.h.a(var2.am));
        var2.c.a((lg) var2.o);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info(var2.e_() + "[" + var8 + "] logged in with entity id " + var2.F() + " at (" + var2.s + ", " + var2.t + ", " + var2.u + ")");
        lg var9 = this.h.a(var2.am);
        avo var10 = var9.Q();
        cj var11 = var9.N();

        this.a(var2, (lh) null, var9);
        lo var12 = new lo(this.h, var1, var2);

        var12.a((ff) (new gt(var2.F(), var2.c.b(), var10.t(), var9.t.p().a(), var9.ab(), this.p(), var10.u(), var9.R().b("reducedDebugInfo"))));
        var12.a((ff) (new gh("MC|Brand", (new em(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((ff) (new fx(var10.y(), var10.z())));
        var12.a((ff) (new ht(var11)));
        var12.a((ff) (new gx(var2.bH)));
        var12.a((ff) (new hi(var2.bp.d)));
        int var13 = this.h(var3) ? this.m.a(var3) : 0;

        var13 = this.h.T() && this.h.d[0].Q().v() ? 4 : var13;
        var13 = this.t ? 4 : var13;
        this.b(var2, var13);
        var2.A().d();
        var2.A().b(var2);
        this.a((kl) var9.aa(), var2);
        this.h.aH();
        fb var14;

        if (!var2.e_().equalsIgnoreCase(var6)) {
            var14 = new fb("multiplayer.player.joined.renamed", new Object[] { var2.f_(), var6});
        } else {
            var14 = new fb("multiplayer.player.joined", new Object[] { var2.f_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var14.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((eu) var14);
        this.c(var2);
        var12.a(var2.s, var2.t, var2.u, var2.y, var2.z);
        this.b(var2, var9);
        if (!this.h.ab().isEmpty()) {
            var2.a(this.h.ab(), this.h.ac());
        }

        Iterator var15 = var2.bm().iterator();

        while (var15.hasNext()) {
            pl var16 = (pl) var15.next();

            var12.a((ff) (new ib(var2.F(), var16)));
        }

        var2.g_();
        if (var7 != null && var7.b("Riding", 10)) {
            pr var17 = pt.a(var7.n("Riding"), (aeo) var9);

            if (var17 != null) {
                var17.n = true;
                var9.a(var17);
                var2.a(var17);
                var17.n = false;
            }
        }

    }

    protected void a(kl var1, lh var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            awl var5 = (awl) var4.next();

            var2.a.a((ff) (new hr(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            awk var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    ff var8 = (ff) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(lg[] var1) {
        this.p = var1[0].P().e();
        var1[0].ag().a(new aod() {
            public void a(aof var1, double var2) {
                lz.this.a((ff) (new hg(var1, hg.a.a))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void a(aof var1, double var2, double var4, long var6) {
                lz.this.a((ff) (new hg(var1, hg.a.b))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void a(aof var1, double var2, double var4) {
                lz.this.a((ff) (new hg(var1, hg.a.c))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void a(aof var1, int var2) {
                lz.this.a((ff) (new hg(var1, hg.a.e))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void b(aof var1, int var2) {
                lz.this.a((ff) (new hg(var1, hg.a.f))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void b(aof var1, double var2) {}

            public void c(aof var1, double var2) {}
        });
    }

    public void a(lh var1, lg var2) {
        lg var3 = var1.u();

        if (var2 != null) {
            var2.u().c(var1);
        }

        var3.u().a(var1);
        var3.b.c((int) var1.s >> 4, (int) var1.u >> 4);
    }

    public int d() {
        return ld.b(this.s());
    }

    public dn a(lh var1) {
        dn var2 = this.h.d[0].Q().i();
        dn var3;

        if (var1.e_().equals(this.h.S()) && var2 != null) {
            var1.f(var2);
            var3 = var2;
            f.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(lh var1) {
        this.p.a(var1);
        mx var2 = (mx) this.o.get(var1.aM());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(lh var1) {
        this.i.add(var1);
        this.j.put(var1.aM(), var1);
        this.a((ff) (new gz(gz.a.a, new lh[] { var1}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
        lg var2 = this.h.a(var1.am);

        var2.a((pr) var1);
        this.a(var1, (lg) null);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lh var4 = (lh) this.i.get(var3);

            var1.a.a((ff) (new gz(gz.a.a, new lh[] { var4}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
        }

    }

    public void d(lh var1) {
        var1.u().u().d(var1);
    }

    public void e(lh var1) {
        var1.b(nc.f);
        this.b(var1);
        lg var2 = var1.u();

        if (var1.m != null) {
            var2.f(var1.m);
            f.debug("removing player mount");
        }

        var2.e(var1);
        var2.u().c(var1);
        this.i.remove(var1);
        UUID var3 = var1.aM();
        lh var4 = (lh) this.j.get(var3);

        if (var4 == var1) {
            this.j.remove(var3);
            this.o.remove(var3);
        }

        this.a((ff) (new gz(gz.a.e, new lh[] { var1}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            mf var5 = (mf) ((md) this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to md

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            lx var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var3.c());
            }

            return var4;
        } else {
            return this.i.size() >= this.e && !this.f(var2) ? "The server is full!" : null;
        }
    }

    public lh g(GameProfile var1) {
        UUID var2 = xa.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            lh var5 = (lh) this.i.get(var4);

            if (var5.aM().equals(var2)) {
                var3.add(var5);
            }
        }

        lh var7 = (lh) this.j.get(var1.getId());

        if (var7 != null && !var3.contains(var7)) {
            var3.add(var7);
        }

        Iterator var8 = var3.iterator();

        while (var8.hasNext()) {
            lh var6 = (lh) var8.next();

            var6.a.c("You logged in from another location");
        }

        Object var9;

        if (this.h.X()) {
            var9 = new kz(this.h.a(0));
        } else {
            var9 = new li(this.h.a(0));
        }

        return new lh(this.h, this.h.a(0), var1, (li) var9);
    }

    public lh a(lh var1, int var2, boolean var3) {
        var1.u().t().b(var1);
        var1.u().t().b((pr) var1);
        var1.u().u().c(var1);
        this.i.remove(var1);
        this.h.a(var1.am).f(var1);
        cj var4 = var1.cj();
        boolean var5 = var1.ck();

        var1.am = var2;
        Object var6;

        if (this.h.X()) {
            var6 = new kz(this.h.a(var1.am));
        } else {
            var6 = new li(this.h.a(var1.am));
        }

        lh var7 = new lh(this.h, this.h.a(var1.am), var1.cf(), (li) var6);

        var7.a = var1.a;
        var7.a((xa) var1, var3);
        var7.e(var1.F());
        var7.o(var1);
        var7.a(var1.bR());
        lg var8 = this.h.a(var1.am);

        this.a(var7, var1, var8);
        cj var9;

        if (var4 != null) {
            var9 = xa.a(this.h.a(var1.am), var4, var5);
            if (var9 != null) {
                var7.b((double) ((float) var9.n() + 0.5F), (double) ((float) var9.o() + 0.1F), (double) ((float) var9.p() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((ff) (new gm(0, 0.0F)));
            }
        }

        var8.b.c((int) var7.s >> 4, (int) var7.u >> 4);

        while (!var8.a((pr) var7, var7.aT()).isEmpty() && var7.t < 256.0D) {
            var7.b(var7.s, var7.t + 1.0D, var7.u);
        }

        var7.a.a((ff) (new he(var7.am, var7.o.ab(), var7.o.Q().u(), var7.c.b())));
        var9 = var8.N();
        var7.a.a(var7.s, var7.t, var7.u, var7.y, var7.z);
        var7.a.a((ff) (new ht(var9)));
        var7.a.a((ff) (new ho(var7.bK, var7.bJ, var7.bI)));
        this.b(var7, var8);
        var8.u().a(var7);
        var8.a((pr) var7);
        this.i.add(var7);
        this.j.put(var7.aM(), var7);
        var7.g_();
        var7.i(var7.bo());
        return var7;
    }

    public void a(lh var1, int var2) {
        int var3 = var1.am;
        lg var4 = this.h.a(var1.am);

        var1.am = var2;
        lg var5 = this.h.a(var1.am);

        var1.a.a((ff) (new he(var1.am, var1.o.ab(), var1.o.Q().u(), var1.c.b())));
        var4.f(var1);
        var1.I = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.s, var1.t, var1.u, var1.y, var1.z);
        var1.c.a(var5);
        this.b(var1, var5);
        this.f(var1);
        Iterator var6 = var1.bm().iterator();

        while (var6.hasNext()) {
            pl var7 = (pl) var6.next();

            var1.a.a((ff) (new ib(var1.F(), var7)));
        }

    }

    public void a(pr var1, int var2, lg var3, lg var4) {
        double var5 = var1.s;
        double var7 = var1.u;
        double var9 = 8.0D;
        float var11 = var1.y;

        var3.B.a("moving");
        if (var1.am == -1) {
            var5 = nu.a(var5 / var9, var4.ag().b() + 16.0D, var4.ag().d() - 16.0D);
            var7 = nu.a(var7 / var9, var4.ag().c() + 16.0D, var4.ag().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        } else if (var1.am == 0) {
            var5 = nu.a(var5 * var9, var4.ag().b() + 16.0D, var4.ag().d() - 16.0D);
            var7 = nu.a(var7 * var9, var4.ag().c() + 16.0D, var4.ag().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        } else {
            cj var12;

            if (var2 == 1) {
                var12 = var4.N();
            } else {
                var12 = var4.n();
            }

            var5 = (double) var12.n();
            var1.t = (double) var12.o();
            var7 = (double) var12.p();
            var1.b(var5, var1.t, var7, 90.0F, 0.0F);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        }

        var3.B.b();
        if (var2 != 1) {
            var3.B.a("placing");
            var5 = (double) nu.a((int) var5, -29999872, 29999872);
            var7 = (double) nu.a((int) var7, -29999872, 29999872);
            if (var1.ai()) {
                var1.b(var5, var1.t, var7, var1.y, var1.z);
                var4.v().a(var1, var11);
                var4.a(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((aeo) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((ff) (new gz(gz.a.c, this.i))); // VanillaIRC -- faulty decompilation: gz -> gz.a
            this.u = 0;
        }

    }

    public void a(ff var1) {
        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            ((lh) this.i.get(var2)).a.a(var1);
        }

    }

    public void a(ff var1, int var2) {
        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lh var4 = (lh) this.i.get(var3);

            if (var4.am == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(xa var1, eu var2) {
        awq var3 = var1.bP();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                lh var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(xa var1, eu var2) {
        awq var3 = var1.bP();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                lh var5 = (lh) this.i.get(var4);

                if (var5.bP() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String b(boolean var1) {
        String var2 = "";
        ArrayList var3 = Lists.newArrayList(this.i);

        for (int var4 = 0; var4 < var3.size(); ++var4) {
            if (var4 > 0) {
                var2 = var2 + ", ";
            }

            var2 = var2 + ((lh) var3.get(var4)).e_();
            if (var1) {
                var2 = var2 + " (" + ((lh) var3.get(var4)).aM().toString() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lh) this.i.get(var2)).e_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lh) this.i.get(var2)).cf();
        }

        return var1;
    }

    public me h() {
        return this.k;
    }

    public lw i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        int var2 = this.h.p();

        ((md) this.m).a((mc) (new mb(var1, this.h.p(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to md
        this.b(this.a(var1.getId()), var2);
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
        this.b(this.a(var1.getId()), 0);
    }

    private void b(lh var1, int var2) {
        if (var1 != null && var1.a != null) {
            byte var3 = var2 <= 0 ? 24 : (var2 >= 4 ? 28 : (byte) (24 + var2));

            var1.a.a((ff) (new gj(var1, var3)));
        }

    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.T() && this.h.d[0].Q().v() && this.h.S().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public lh a(String var1) {
        Iterator var2 = this.i.iterator();

        lh var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (lh) var2.next();
        } while (!var3.e_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, ff var10) {
        this.a((xa) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(xa var1, double var2, double var4, double var6, double var8, int var10, ff var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            lh var13 = (lh) this.i.get(var12);

            if (var13 != var1 && var13.am == var10) {
                double var14 = var2 - var13.s;
                double var16 = var4 - var13.t;
                double var18 = var6 - var13.u;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void j() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            this.b((lh) this.i.get(var1));
        }

    }

    public void d(GameProfile var1) {
        ((md) this.n).a((mc) (new mh(var1))); // VanillaIRC -- obfuscation anomaly: cast this.m to md
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public mg k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public ma m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(lh var1, lg var2) {
        aof var3 = this.h.d[0].ag();

        var1.a.a((ff) (new hg(var3, hg.a.d))); // VanillaIRC -- faulty decompilation: hg -> hg.a
        var1.a.a((ff) (new hu(var2.L(), var2.M(), var2.R().b("doDaylightCycle"))));
        if (var2.T()) {
            var1.a.a((ff) (new gm(1, 0.0F)));
            var1.a.a((ff) (new gm(7, var2.j(1.0F))));
            var1.a.a((ff) (new gm(8, var2.h(1.0F))));
        }

    }

    public void f(lh var1) {
        var1.a(var1.bq);
        var1.r();
        var1.a.a((ff) (new hi(var1.bp.d)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].P().e().f();
    }

    public boolean r() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List<lh> b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.i.iterator();

        while (var3.hasNext()) {
            lh var4 = (lh) var3.next();

            if (var4.w().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int s() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.h;
    }

    public dn t() {
        return null;
    }

    private void a(lh var1, lh var2, aeo var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.Q().r());
    }

    public void u() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            ((lh) this.i.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(eu var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);
        
        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }
    
    public void sendMessageToPlayers(eu var1, boolean var2) {
        this.h.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((ff) (new fz(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(eu.a.a(json), false);
    } // VanillaIRC end

    public void a(eu var1) {
        this.a(var1, true);
    }

    public mx a(xa var1) {
        UUID var2 = var1.aM();
        mx var3 = var2 == null ? null : (mx) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).P().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.e_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new mx(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.d != null) {
            lg[] var2 = this.h.d;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                lg var5 = var2[var4];

                if (var5 != null) {
                    var5.u().a(var1);
                }
            }

        }
    }

    public List<lh> v() {
        return this.i;
    }

    public lh a(UUID var1) {
        return (lh) this.j.get(var1);
    }

    public boolean f(GameProfile var1) {
        return false;
    }

}
