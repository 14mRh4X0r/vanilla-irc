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


public abstract class lx {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lf> i = Lists.newArrayList();
    private final Map<UUID, lf> j = Maps.newHashMap();
    private final mc k;
    private final lu l;
    private final ly m;
    private final me n;
    private final Map<UUID, mv> o;
    private aty p;
    private boolean q;
    protected int e;
    private int r;
    private adp.a s; // VanillaIRC -- faulty decompilation: adp -> adp.a
    private boolean t;
    private int u;

    public lx(MinecraftServer var1) {
        this.k = new mc(a);
        this.l = new lu(b);
        this.m = new ly(c);
        this.n = new me(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(ek var1, lf var2) {
        GameProfile var3 = var2.cd();
        lt var4 = this.h.aF();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dn var7 = this.a(var2);

        var2.a((adm) this.h.a(var2.am));
        var2.c.a((le) var2.o);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info(var2.e_() + "[" + var8 + "] logged in with entity id " + var2.F() + " at (" + var2.s + ", " + var2.t + ", " + var2.u + ")");
        le var9 = this.h.a(var2.am);
        ato var10 = var9.P();
        cj var11 = var9.M();

        this.a(var2, (lf) null, var9);
        lm var12 = new lm(this.h, var1, var2);

        var12.a((ff) (new gt(var2.F(), var2.c.b(), var10.t(), var9.t.q(), var9.aa(), this.p(), var10.u(), var9.Q().b("reducedDebugInfo"))));
        var12.a((ff) (new gg("MC|Brand", (new em(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((ff) (new fw(var10.y(), var10.z())));
        var12.a((ff) (new ht(var11)));
        var12.a((ff) (new gx(var2.bA)));
        var12.a((ff) (new hi(var2.bi.c)));
        var2.A().d();
        var2.A().b(var2);
        this.a((kk) var9.Z(), var2);
        this.h.aH();
        fb var13;

        if (!var2.e_().equalsIgnoreCase(var6)) {
            var13 = new fb("multiplayer.player.joined.renamed", new Object[] { var2.f_(), var6});
        } else {
            var13 = new fb("multiplayer.player.joined", new Object[] { var2.f_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((eu) var13);
        this.c(var2);
        var12.a(var2.s, var2.t, var2.u, var2.y, var2.z);
        this.b(var2, var9);
        if (this.h.ab().length() > 0) {
            var2.a(this.h.ab(), this.h.ac());
        }

        Iterator var14 = var2.bl().iterator();

        while (var14.hasNext()) {
            pf var15 = (pf) var14.next();

            var12.a((ff) (new ib(var2.F(), var15)));
        }

        var2.g_();
        if (var7 != null && var7.b("Riding", 10)) {
            pk var16 = pm.a(var7.m("Riding"), (adm) var9);

            if (var16 != null) {
                var16.n = true;
                var9.d(var16);
                var2.a(var16);
                var16.n = false;
            }
        }

    }

    protected void a(kk var1, lf var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            aul var5 = (aul) var4.next();

            var2.a.a((ff) (new hr(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            auk var10 = var1.a(var9);

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

    public void a(le[] var1) {
        this.p = var1[0].O().e();
        var1[0].af().a(new amq() {
            public void a(ams var1, double var2) {
                lx.this.a((ff) (new hg(var1, hg.a.a))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void a(ams var1, double var2, double var4, long var6) {
                lx.this.a((ff) (new hg(var1, hg.a.b))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void a(ams var1, double var2, double var4) {
                lx.this.a((ff) (new hg(var1, hg.a.c))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void a(ams var1, int var2) {
                lx.this.a((ff) (new hg(var1, hg.a.e))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void b(ams var1, int var2) {
                lx.this.a((ff) (new hg(var1, hg.a.f))); // VanillaIRC -- faulty decompilation: hg -> hg.a
            }

            public void b(ams var1, double var2) {}

            public void c(ams var1, double var2) {}
        });
    }

    public void a(lf var1, le var2) {
        le var3 = var1.u();

        if (var2 != null) {
            var2.t().c(var1);
        }

        var3.t().a(var1);
        var3.b.c((int) var1.s >> 4, (int) var1.u >> 4);
    }

    public int d() {
        return lc.b(this.s());
    }

    public dn a(lf var1) {
        dn var2 = this.h.d[0].P().i();
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

    protected void b(lf var1) {
        this.p.a(var1);
        mv var2 = (mv) this.o.get(var1.aK());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(lf var1) {
        this.i.add(var1);
        this.j.put(var1.aK(), var1);
        this.a((ff) (new gz(gz.a.a, new lf[] { var1}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
        le var2 = this.h.a(var1.am);

        var2.d(var1);
        this.a(var1, (le) null);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lf var4 = (lf) this.i.get(var3);

            var1.a.a((ff) (new gz(gz.a.a, new lf[] { var4}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
        }

    }

    public void d(lf var1) {
        var1.u().t().d(var1);
    }

    public void e(lf var1) {
        var1.b(na.f);
        this.b(var1);
        le var2 = var1.u();

        if (var1.m != null) {
            var2.f(var1.m);
            f.debug("removing player mount");
        }

        var2.e(var1);
        var2.t().c(var1);
        this.i.remove(var1);
        UUID var3 = var1.aK();
        lf var4 = (lf) this.j.get(var3);

        if (var4 == var1) {
            this.j.remove(var3);
            this.o.remove(var3);
        }

        this.a((ff) (new gz(gz.a.e, new lf[] { var1}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            md var5 = (md) ((mb)this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to mb

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            lv var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var3.c());
            }

            return var4;
        } else {
            return this.i.size() >= this.e && !this.f(var2) ? "The server is full!" : null;
        }
    }

    public lf g(GameProfile var1) {
        UUID var2 = wn.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            lf var5 = (lf) this.i.get(var4);

            if (var5.aK().equals(var2)) {
                var3.add(var5);
            }
        }

        lf var7 = (lf) this.j.get(var1.getId());

        if (var7 != null && !var3.contains(var7)) {
            var3.add(var7);
        }

        Iterator var8 = var3.iterator();

        while (var8.hasNext()) {
            lf var6 = (lf) var8.next();

            var6.a.c("You logged in from another location");
        }

        Object var9;

        if (this.h.X()) {
            var9 = new ky(this.h.a(0));
        } else {
            var9 = new lg(this.h.a(0));
        }

        return new lf(this.h, this.h.a(0), var1, (lg) var9);
    }

    public lf a(lf var1, int var2, boolean var3) {
        var1.u().s().b(var1);
        var1.u().s().b((pk) var1);
        var1.u().t().c(var1);
        this.i.remove(var1);
        this.h.a(var1.am).f(var1);
        cj var4 = var1.ch();
        boolean var5 = var1.ci();

        var1.am = var2;
        Object var6;

        if (this.h.X()) {
            var6 = new ky(this.h.a(var1.am));
        } else {
            var6 = new lg(this.h.a(var1.am));
        }

        lf var7 = new lf(this.h, this.h.a(var1.am), var1.cd(), (lg) var6);

        var7.a = var1.a;
        var7.a((wn) var1, var3);
        var7.d(var1.F());
        var7.o(var1);
        le var8 = this.h.a(var1.am);

        this.a(var7, var1, var8);
        cj var9;

        if (var4 != null) {
            var9 = wn.a(this.h.a(var1.am), var4, var5);
            if (var9 != null) {
                var7.b((double) ((float) var9.n() + 0.5F), (double) ((float) var9.o() + 0.1F), (double) ((float) var9.p() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((ff) (new gm(0, 0.0F)));
            }
        }

        var8.b.c((int) var7.s >> 4, (int) var7.u >> 4);

        while (!var8.a((pk) var7, var7.aR()).isEmpty() && var7.t < 256.0D) {
            var7.b(var7.s, var7.t + 1.0D, var7.u);
        }

        var7.a.a((ff) (new he(var7.am, var7.o.aa(), var7.o.P().u(), var7.c.b())));
        var9 = var8.M();
        var7.a.a(var7.s, var7.t, var7.u, var7.y, var7.z);
        var7.a.a((ff) (new ht(var9)));
        var7.a.a((ff) (new ho(var7.bD, var7.bC, var7.bB)));
        this.b(var7, var8);
        var8.t().a(var7);
        var8.d(var7);
        this.i.add(var7);
        this.j.put(var7.aK(), var7);
        var7.g_();
        var7.i(var7.bn());
        return var7;
    }

    public void a(lf var1, int var2) {
        int var3 = var1.am;
        le var4 = this.h.a(var1.am);

        var1.am = var2;
        le var5 = this.h.a(var1.am);

        var1.a.a((ff) (new he(var1.am, var1.o.aa(), var1.o.P().u(), var1.c.b())));
        var4.f(var1);
        var1.I = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.s, var1.t, var1.u, var1.y, var1.z);
        var1.c.a(var5);
        this.b(var1, var5);
        this.f(var1);
        Iterator var6 = var1.bl().iterator();

        while (var6.hasNext()) {
            pf var7 = (pf) var6.next();

            var1.a.a((ff) (new ib(var1.F(), var7)));
        }

    }

    public void a(pk var1, int var2, le var3, le var4) {
        double var5 = var1.s;
        double var7 = var1.u;
        double var9 = 8.0D;
        float var11 = var1.y;

        var3.B.a("moving");
        if (var1.am == -1) {
            var5 = ns.a(var5 / var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = ns.a(var7 / var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        } else if (var1.am == 0) {
            var5 = ns.a(var5 * var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = ns.a(var7 * var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        } else {
            cj var12;

            if (var2 == 1) {
                var12 = var4.M();
            } else {
                var12 = var4.m();
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
            var5 = (double) ns.a((int) var5, -29999872, 29999872);
            var7 = (double) ns.a((int) var7, -29999872, 29999872);
            if (var1.ai()) {
                var1.b(var5, var1.t, var7, var1.y, var1.z);
                var4.u().a(var1, var11);
                var4.d(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((adm) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((ff) (new gz(gz.a.c, this.i))); // VanillaIRC -- faulty decompilation: gz -> gz.a
            this.u = 0;
        }

    }

    public void a(ff var1) {
        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            ((lf) this.i.get(var2)).a.a(var1);
        }

    }

    public void a(ff var1, int var2) {
        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lf var4 = (lf) this.i.get(var3);

            if (var4.am == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(wn var1, eu var2) {
        auq var3 = var1.bO();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                lf var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(wn var1, eu var2) {
        auq var3 = var1.bO();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                lf var5 = (lf) this.i.get(var4);

                if (var5.bO() != var3) {
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

            var2 = var2 + ((lf) var3.get(var4)).e_();
            if (var1) {
                var2 = var2 + " (" + ((lf) var3.get(var4)).aK().toString() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lf) this.i.get(var2)).e_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lf) this.i.get(var2)).cd();
        }

        return var1;
    }

    public mc h() {
        return this.k;
    }

    public lu i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        ((mb) this.m).a((ma) (new lz(var1, this.h.p(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to mb
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.T() && this.h.d[0].P().v() && this.h.S().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public lf a(String var1) {
        Iterator var2 = this.i.iterator();

        lf var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (lf) var2.next();
        } while (!var3.e_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, ff var10) {
        this.a((wn) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(wn var1, double var2, double var4, double var6, double var8, int var10, ff var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            lf var13 = (lf) this.i.get(var12);

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
            this.b((lf) this.i.get(var1));
        }

    }

    public void d(GameProfile var1) {
        ((mb) this.n).a((ma) (new mf(var1))); // VanillaIRC -- obfuscation anomaly: cast this.n to mb
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public me k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public ly m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(lf var1, le var2) {
        ams var3 = this.h.d[0].af();

        var1.a.a((ff) (new hg(var3, hg.a.d))); // VanillaIRC -- faulty decompilation: hg -> hg.a
        var1.a.a((ff) (new hu(var2.K(), var2.L(), var2.Q().b("doDaylightCycle"))));
        if (var2.S()) {
            var1.a.a((ff) (new gm(1, 0.0F)));
            var1.a.a((ff) (new gm(7, var2.j(1.0F))));
            var1.a.a((ff) (new gm(8, var2.h(1.0F))));
        }

    }

    public void f(lf var1) {
        var1.a(var1.bj);
        var1.r();
        var1.a.a((ff) (new hi(var1.bi.c)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].O().e().f();
    }

    public boolean r() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List<lf> b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.i.iterator();

        while (var3.hasNext()) {
            lf var4 = (lf) var3.next();

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

    private void a(lf var1, lf var2, adm var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.P().r());
    }

    public void u() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            ((lf) this.i.get(var1)).a.c("Server closed");
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

        this.a((ff) (new fy(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(eu.a.a(json), false);
    } // VanillaIRC end

    public void a(eu var1) {
        this.a(var1, true);
    }

    public mv a(wn var1) {
        UUID var2 = var1.aK();
        mv var3 = var2 == null ? null : (mv) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).O().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.e_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new mv(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.d != null) {
            le[] var2 = this.h.d;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                le var5 = var2[var4];

                if (var5 != null) {
                    var5.t().a(var1);
                }
            }

        }
    }

    public List<lf> v() {
        return this.i;
    }

    public lf a(UUID var1) {
        return (lf) this.j.get(var1);
    }

    public boolean f(GameProfile var1) {
        return false;
    }

}
